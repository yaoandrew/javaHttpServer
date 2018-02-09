package handlers;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import messages.Response;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class FileSystemHandlerTest {

  @Rule
  public TemporaryFolder tempFolder = new TemporaryFolder();

  @Ignore
  public void FileSystemHandlerTestReturnsResponseBody() throws IOException {

    String requestString = "GET /file1 HTTP/1.1";
    RequestParser parser = new RequestParser();

    //setup mock file in test to send in response
    File serverFile = tempFolder.newFile("file1");
    FileWriter fileWriter = new FileWriter(serverFile);
    fileWriter.write("File1 contents");
    fileWriter.flush();
    fileWriter.close();

    byte[] actualFile = Files.readAllBytes(serverFile.toPath());

    FileSystemHandler fileSystemHandler = new FileSystemHandler(serverFile);
    Response fileResponse = fileSystemHandler.getResponse(parser.parse(requestString));

    assertEquals(actualFile, fileResponse.getBody());

  }
}
