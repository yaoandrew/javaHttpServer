package handlers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import messages.Response;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class FileSystemHandlerTest {

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void FileSystemHandlerTestReturnsResponseBody() throws IOException {

    String requestString = "GET /file1 HTTP/1.1";

    RequestParser parser = new RequestParser();

    //setup mock file in test to send in response
    File serverFile = tempFolder.newFile("file1");
    FileWriter fileWriter = new FileWriter(serverFile);
    fileWriter.write("file1 contents");
    fileWriter.flush();
    fileWriter.close();

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);

    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals("file1 contents", fileResponse.getBody());

  }

}
