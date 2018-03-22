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

public class FileHandlerTest {

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeJPG() throws IOException {

    String requestString = "GET /image.jpeg HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("image.jpeg");

    FileHandler fileHandler = new FileHandler(serverFile);
    Response fileResponse = fileHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-Type: image/jpeg", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypePNG() throws IOException {

    String requestString = "GET /image.png HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("image.png");

    FileHandler fileHandler = new FileHandler(serverFile);
    Response fileResponse = fileHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-Type: image/png", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeGIF() throws IOException {

    String requestString = "GET /image.gif HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("image.gif");

    FileHandler fileHandler = new FileHandler(serverFile);
    Response fileResponse = fileHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-Type: image/gif", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeTXT() throws IOException {

    String requestString = "GET /text-file.txt HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("text-file.txt");

    FileHandler fileHandler = new FileHandler(serverFile);
    Response fileResponse = fileHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-Type: text/plain", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeNoExtension() throws IOException {

    String requestString = "GET /file1 HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("file1");

    FileHandler fileHandler = new FileHandler(serverFile);
    Response fileResponse = fileHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-Type: application/octet-stream", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturns405() throws IOException {

    String requestString = "POST /file1 HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("file1");

    FileHandler fileHandler = new FileHandler(serverFile);
    Response fileResponse = fileHandler.getResponse(parser.parse(requestString));

    assertEquals(HTTPStatus.NOT_ALLOWED.getStatusLine(), fileResponse.getStatusLine());
  }

  @Test
  public void FileSystemHandlerTestReturns200ForPatchContentTxt() throws IOException {

    String requestString = "GET /patch-content.txt HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("patch-content.txt");

    FileHandler fileHandler = new FileHandler(serverFile);
    Response fileResponse = fileHandler.getResponse(parser.parse(requestString));

    assertEquals(HTTPStatus.OK.getStatusLine(), fileResponse.getStatusLine());
  }

}
