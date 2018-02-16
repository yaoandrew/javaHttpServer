package handlers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class PartialContentHandlerTest {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  RequestParser parser = new RequestParser();

  @Test
  public void PartialContentHandlerCanDetermineContentLength() throws IOException {

    File file = temporaryFolder.newFile("partial_content.txt");
    String content = "This is a file that contains text to read part of in order to fulfill a 206. ";
    Files.write(file.toPath(), content.getBytes());
    PartialContentHandler partialContentHandler = new PartialContentHandler(file);

    assertEquals(content.getBytes().length, partialContentHandler.getContentLength());
  }

  @Test
  public void PartialContentHandlerCanReturnFullRangeGiven() throws IOException {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: 0-4\r\n";
    File file = temporaryFolder.newFile("partial_content.txt");
    String content = "This is a file that contains text to read part of in order to fulfill a 206. ";
    Files.write(file.toPath(), content.getBytes());
    PartialContentHandler partialContentHandler = new PartialContentHandler(file);

    assertThat(Arrays.copyOfRange(content.getBytes(), 0, 5), equalTo(partialContentHandler.getResponse(parser.parse(rawRequest)).getBody()));
  }

  @Test
  public void PartialContentHandlerCanReturnEndRangeGiven() throws IOException {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: -6\r\n";
    File file = temporaryFolder.newFile("partial_content.txt");
    String content = "This is a file that contains text to read part of in order to fulfill a 206. ";
    Files.write(file.toPath(), content.getBytes());
    PartialContentHandler partialContentHandler = new PartialContentHandler(file);

    assertThat(Arrays.copyOfRange(content.getBytes(), 71, 77), equalTo(partialContentHandler.getResponse(parser.parse(rawRequest)).getBody()));
  }

  @Test
  public void PartialContentHandlerCanReturnBeginRangeGiven() throws IOException {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: 4-\r\n";
    File file = temporaryFolder.newFile("partial_content.txt");
    String content = "This is a file that contains text to read part of in order to fulfill a 206. ";
    Files.write(file.toPath(), content.getBytes());
    PartialContentHandler partialContentHandler = new PartialContentHandler(file);

    assertThat(Arrays.copyOfRange(content.getBytes(), 4, 77), equalTo(partialContentHandler.getResponse(parser.parse(rawRequest)).getBody()));
  }
}
