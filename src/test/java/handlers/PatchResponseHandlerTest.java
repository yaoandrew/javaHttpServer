package handlers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import messages.HTTPStatus;
import messages.Response;
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
    String requestString = "PATCH /patch-content.txt HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("patch-content.txt");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals(HTTPStatus.NO_CONTENT.getStatusLine(), fileResponse.getStatusLine());
  }

  @Test
  public void PatchChangesResourceIfEtagMatches() throws IOException {

  }
}
