package handlers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import parsers.RequestParser;

public class CookieHandlerTest {

  private RequestParser parser = new RequestParser();

  @Test
  public void CookieHandlerReturnsCorrectBody() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    CookieHandler ch = new CookieHandler(parser.parse(requestString));
    String expected = "Eat";
    String actual = ch.getResponse().getBody();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerReturnsCorrectHeader() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    CookieHandler ch = new CookieHandler(parser.parse(requestString));
    String expected = "Set-Cookie: chocolate";
    String actual = ch.getResponse().getHeaders();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerUsesCookie() {
    String requestString = "GET /eat_cookie HTTP/1.1";
    CookieHandler ch = new CookieHandler(parser.parse(requestString));
    String expected = "mmmm chocolate";
    String actual = ch.getResponse().getBody();

    assertEquals(expected, actual);
  }
}