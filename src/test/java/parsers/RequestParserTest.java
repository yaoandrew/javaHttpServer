package parsers;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {

  private String testString = "GET /foobar HTTP/1.1";

  @Test
  public void ParserReturnsHTTPMethod() {
    assertEquals("GET", RequestParser.getHttpMethod(testString));
  }

  @Test
  public void ParserReturnsURI() {
    assertEquals("/foobar", RequestParser.getURI(testString));
  }

  @Test
  public void ParserReturnsRawUri() {
    String testStringWithParams = "POST /params?greeting=hello HTTP/1.1";
    String expected = "/params?greeting=hello";

    assertEquals(expected, RequestParser.getRawUri(testStringWithParams));

  }

  @Test
  public void ParserReturnsURIWithoutParam() {
    String testStringWithParams = "POST /params?greeting=hello HTTP/1.1";

    assertEquals("/params", RequestParser.getURI(testStringWithParams));
  }

  @Test
  public void ParserReturnsHTTPVersion() {
    assertEquals("HTTP/1.1", RequestParser.getHttpVersion(testString));
  }

  @Test
  public void ParserReturnsParams() {
    String testStringWithParams = "POST /params?greeting=hello HTTP/1.1";

    assertEquals("greeting=hello", RequestParser.getParams(testStringWithParams));
  }

  @Test
  public void ParserReturnsStatusAndHeaders() {
    String testStringWithHeaders = "GET / HTTP/1.1\r\nHello: World\r\nContent: text\r\n\r\nBody";
    String [] statusAndHeaders = {"GET / HTTP/1.1", "Hello: World", "Content: text", "", "Body"};

    assertArrayEquals(RequestParser.getStatusHeaders(testStringWithHeaders), statusAndHeaders);

  }

  @Test
  public void ParserReturnsStatusAndHeadersNoBody() {
    String testStringWithHeaders = "GET / HTTP/1.1\r\nHello: World\r\nContent: text\r\n\r\n";
    String [] expectedStatusAndHeaders = {"GET / HTTP/1.1", "Hello: World", "Content: text"};

    assertArrayEquals(expectedStatusAndHeaders, RequestParser.getStatusHeaders(testStringWithHeaders));

  }

  @Test
  public void ParserReturnsStatusWithoutHeaders() {
    String testStringNoHeaders = "GET / HTTP/1.1\r\n\r\nBody";
    String [] statusAndBody = {"GET / HTTP/1.1", "", "Body"};

    assertArrayEquals(RequestParser.getStatusHeaders(testStringNoHeaders), statusAndBody);

  }

  @Test
  public void ParserReturnsStatusWithoutHeadersOrBody() {
    String testStringNoHeaders = "GET / HTTP/1.1\r\n";
    String [] statusAndBody = {"GET / HTTP/1.1"};

    assertArrayEquals(RequestParser.getStatusHeaders(testStringNoHeaders), statusAndBody);

  }
}