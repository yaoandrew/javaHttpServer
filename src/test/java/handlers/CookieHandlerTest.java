package handlers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import messages.Request;
import parsers.RequestParser;

public class CookieHandlerTest {

  @Test
  public void CookieHandlerReturnsCorrectBody() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    RequestParser parser = new RequestParser();
    Request parsedRequest = parser.parse(requestString);
    CookieHandler ch = new CookieHandler(parsedRequest);
    String expected = "Eat";
    String actual = ch.getResponse().getBody();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerReturnsCorrectHeader() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    RequestParser parser = new RequestParser();
    Request parsedRequest = parser.parse(requestString);
    CookieHandler ch = new CookieHandler(parsedRequest);
    String expected = "Set-Cookie: chocolate";
    String actual = ch.getResponse().getHeaders();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerUsesCookie() {

    String requestString = "GET /eat_cookie HTTP/1.1";
    RequestParser parser = new RequestParser();
    Request parsedRequest = parser.parse(requestString);
    CookieHandler ch = new CookieHandler(parsedRequest);
    String expected = "mmmm chocolate";
    String actual = ch.getResponse().getBody();

    assertEquals(expected, actual);
  }
}