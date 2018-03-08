package handlers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import messages.HTTPStatus;
import messages.Response;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class PatchResponseHandlerTest {

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void GetReturnsStatus200() throws IOException {
    String requestString = "GET /patch-content.txt HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("patch-content.txt");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals(HTTPStatus.OK.getStatusLine(), fileResponse.getStatusLine());
  }

  @Test
  public void PatchReturnsStatus204() throws IOException {
    String sha1Hash = "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec";
    String requestString = "PATCH /patch-content.txt HTTP/1.1\r\nIf-Match: " + sha1Hash +
      "\r\n\r\npatched content";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("patch-content.txt");
    FileWriter fileWriter = new FileWriter(serverFile);
    fileWriter.write("default content");
    fileWriter.close();

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals(HTTPStatus.NO_CONTENT.getStatusLine(), fileResponse.getStatusLine());
  }

  @Test
  public void PatchChangesResourceIfEtagMatches() throws IOException {
    String sha1Hash = "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec";
    String requestString  = "PATCH /patch-content.txt HTTP/1.1\r\nIf-Match: " +
        sha1Hash + "\r\n\r\npatched content";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("patch-content.txt");
    FileWriter fileWriter = new FileWriter(serverFile);
    fileWriter.write("default content");
    fileWriter.close();

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertThat(new String(fileResponse.getBody()), equalTo("patched content"));
  }

}
