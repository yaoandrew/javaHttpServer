package handlers;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import messages.Request;

public class CookieHandlerTest {

  @Test
  public void CookieHandlerReturnsCorrectBody() {
    Request request = new Request("GET /cookie?type=chocolate HTTP/1.1");
    CookieHandler ch = new CookieHandler(request);
    String expected = "Eat";
    String actual = ch.getResponse().getBody();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerReturnsCorrectHeader() {
    Request request = new Request("GET /cookie?type=chocolate HTTP/1.1");
    CookieHandler ch = new CookieHandler(request);
    String expected = "Set-Cookie: chocolate";
    String actual = ch.getResponse().getHeaders();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerUsesCookie() {
    Request request = new Request("GET /eat_cookie HTTP/1.1");
    CookieHandler ch = new CookieHandler(request);
    String expected = "mmmm chocolate";
    String actual = ch.getResponse().getBody();

    assertEquals(expected, actual);
  }
}