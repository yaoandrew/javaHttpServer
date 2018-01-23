package parsers;

import java.util.Arrays;
import messages.Request;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {

  RequestParser parser = new RequestParser();

  @Test
  public void ParserTakesRawRequestStringAndReturnsRequestObject() {

    String testString = "GET /foobar HTTP/1.1";

    assertTrue(parser.parse(testString) instanceof Request);
  }

  @Test
  public void ParserSetsHttpMethodInRequestObject() {

    String testString = "GET /aboutus HTTP/1.1";

    assertEquals("GET", parser.parse(testString).getHttpMethod());
  }

  @Test
  public void ParserSetsURIInRequestObject() {

    String testString = "GET /aboutus HTTP/1.1";

    assertEquals("/aboutus", parser.parse(testString).getRawUri());

  }

  @Test
  public void ParserSetsHTTPVersionInRequestObject() {

    String testString = "GET /aboutus HTTP/1.1";

    assertEquals("HTTP/1.1", parser.parse(testString).getHttpVersion());

  }

  @Test
  public void ParserSetsHeadersInRequestObject() {

    String testString = "GET /aboutus HTTP/1.1\r\nHello : World\r\nContent : text\r\n\r\n";

    assertEquals("text", parser.parse(testString).getHeaderValue("Content"));

  }

  @Test
  public void ParserSetsParamsInRequestObject() {

    String testStringWithParams = "POST /params?greeting=hello HTTP/1.1";
    String[] expected = {"greeting=hello"};

    assertTrue(Arrays.equals(expected, parser.parse(testStringWithParams).getParams()));

  }

  @Test
  public void ParserSetsMultipleParamsInRequestObject() {

    String testStringWithParams = "POST /params?greeting=hello&variable2=more HTTP/1.1";
    String[] expected = {"greeting=hello", "variable2=more"};

    assertTrue(Arrays.equals(expected, parser.parse(testStringWithParams).getParams()));

  }

  @Test
  public void ParserSetsCookieInRequestObject() {

    String testStringWithParams = "POST /cookie?type=chocolate HTTP/1.1";

    assertEquals("chocolate", parser.parse(testStringWithParams).getCookie());
  }

/**

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

  }**/
}