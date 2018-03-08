package handlers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import messages.HTTPStatus;
import messages.Response;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class FileSystemHandlerTest {

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeJPG() throws IOException {

    String requestString = "GET /image.jpeg HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("image.jpeg");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-type: image/jpeg", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypePNG() throws IOException {

    String requestString = "GET /image.png HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("image.png");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-type: image/png", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeGIF() throws IOException {

    String requestString = "GET /image.gif HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("image.gif");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-type: image/gif", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeTXT() throws IOException {

    String requestString = "GET /text-file.txt HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("text-file.txt");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-type: text/plain", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturnsCorrectContentTypeNoExtension() throws IOException {

    String requestString = "GET /file1 HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("file1");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals("Content-type: application/octet-stream", fileResponse.getHeaders());
  }

  @Test
  public void FileSystemHandlerTestReturns405() throws IOException {

    String requestString = "POST /file1 HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("file1");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals(HTTPStatus.NOT_ALLOWED.getStatusLine(), fileResponse.getStatusLine());
  }

  @Test
  public void FileSystemHandlerTestReturns200ForPatchContentTxt() throws IOException {

    String requestString = "GET /patch-content.txt HTTP/1.1";
    RequestParser parser = new RequestParser();
    File serverFile = tempFolder.newFile("patch-content.txt");

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals(HTTPStatus.OK.getStatusLine(), fileResponse.getStatusLine());
  }

}
