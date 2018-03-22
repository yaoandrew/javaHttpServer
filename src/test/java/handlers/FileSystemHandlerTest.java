package handlers;

import java.io.File;
import java.io.IOException;
import messages.Request;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class FileSystemHandlerTest {

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void ReturnsCorrectHandlerIfFileExists() throws IOException {

    String requestString = "GET /image.jpeg HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("image.jpeg");
    Request request = parser.parse(requestString);

//    FileSystemHandler = new FileSystemHandler(request, file);


  }

}
