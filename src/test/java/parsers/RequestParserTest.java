package parsers;

import messages.Request;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {

  private RequestParser parser = new RequestParser();

  @Test
  public void ParserTakesRawRequestStringAndReturnsRequestObject() {

    String testString = "GET /foobar HTTP/1.1";

    assertTrue(parser.parse(testString) != null);
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
    String expected = "hello";

    assertEquals(expected, parser.parse(testStringWithParams).getParamValue("greeting"));

  }

  @Test
  public void ParserSetsCookieInRequestObject() {

    String testStringWithParams = "POST /cookie?type=chocolate HTTP/1.1";

    assertEquals("chocolate", parser.parse(testStringWithParams).getCookie());
  }
}