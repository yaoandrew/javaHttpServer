package handlers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import parsers.RequestParser;

public class CookieHandlerTest {

  private RequestParser parser = new RequestParser();

  @Test
  public void CookieHandlerReturnsCorrectBody() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    CookieHandler ch = new CookieHandler();
    String expected = "Eat";
    String actual = ch.getResponse(parser.parse(requestString)).getBody();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerReturnsCorrectHeader() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    CookieHandler ch = new CookieHandler();
    String expected = "Set-Cookie: type=chocolate";
    String actual = ch.getResponse(parser.parse(requestString)).getHeaders();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerUsesCookie() {
    String requestString = "GET /eat_cookie HTTP/1.1\r\nCookie: type=chocolate\r\n";
    CookieHandler ch = new CookieHandler();
    String expected = "mmmm chocolate";
    String actual = ch.getResponse(parser.parse(requestString)).getBody();

    assertEquals(expected, actual);
  }
}