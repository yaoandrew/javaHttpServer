package handlers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import messages.HTTPStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import parsers.RequestParser;

public class PartialContentHandlerTest {

  @Rule
  public TemporaryFolder temporaryFolder = new TemporaryFolder();

  RequestParser parser = new RequestParser();
  PartialContentHandler partialContentHandler;
  private String content;

  @Before
  public void setup() throws IOException {
    content = "This is a file that contains text to read part of in order to fulfill a 206. ";
    File file = temporaryFolder.newFile("partial_content.txt");
    Files.write(file.toPath(), content.getBytes());
    partialContentHandler = new PartialContentHandler(file);
  }

  @Test
  public void DeterminesContentLength() {

    assertEquals(content.getBytes().length, partialContentHandler.getContentLength());
  }

  @Test
  public void ReturnsFullRangeGiven() {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=0-4\r\n";

    assertThat(new String (partialContentHandler.getResponse(parser.parse(rawRequest)).getBody()),
        equalTo(content.substring(0, 5)));
  }

  @Test
  public void ReturnsEndRangeGiven() {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=-6\r\n";

    assertThat(new String (partialContentHandler.getResponse(parser.parse(rawRequest)).getBody()),
        equalTo(content.substring(71, 77)));
  }

  @Test
  public void ReturnsBeginRangeGiven() {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=4-\r\n";

    assertThat(new String(partialContentHandler.getResponse(parser.parse(rawRequest)).getBody()),
      equalTo(content.substring(4, 77)));
  }

  @Test
  public void HandlesInvalidNumericRangeInput() {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=4-88\r\n";

    assertThat(partialContentHandler.getResponse((parser.parse(rawRequest))).getStatusLine(),
      equalTo(HTTPStatus.RANGE_NOT_SATISFIABLE.getStatusLine()));
  }

  @Test
  public void HandlesInvalidRangeInput() {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=blah\r\n";

    assertThat(partialContentHandler.getResponse((parser.parse(rawRequest))).getStatusLine(),
        equalTo(HTTPStatus.RANGE_NOT_SATISFIABLE.getStatusLine()));
  }

  @Test
  public void HandlesInvalidRangeWithADash() {

    String rawRequest = "GET /partial_content.txt HTTP/1.1\r\nRange: bytes=-blah\r\n";

    assertThat(partialContentHandler.getResponse((parser.parse(rawRequest))).getStatusLine(),
        equalTo(HTTPStatus.RANGE_NOT_SATISFIABLE.getStatusLine()));
  }
}
