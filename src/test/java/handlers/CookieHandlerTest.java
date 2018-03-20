package handlers;

import messages.HTTPStatus;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import parsers.RequestParser;

public class CookieHandlerTest {

  private RequestParser parser = new RequestParser();

  @Test
  public void CookieHandlerReturnsCorrectBodyWhenCookieSentAsParam() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    CookieHandler ch = new CookieHandler();
    String expected = "Eat";
    String actual = new String (ch.getResponse(parser.parse(requestString)).getBody());

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerReturnsCorrectHeaderWhenCookieSentAsParam() {
    String requestString = "GET /cookie?type=chocolate HTTP/1.1";
    CookieHandler ch = new CookieHandler();
    String expected = "Set-Cookie: type=chocolate";
    String actual = ch.getResponse(parser.parse(requestString)).getHeaders();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerReturnsCorrectHeaderWhenMultipleCookiesSentAsParams() {
    String requestString = "GET /cookie?foo=bar&type=chocolate HTTP/1.1";
    CookieHandler ch = new CookieHandler();
    String expected = "Set-Cookie: type=chocolate";
    String actual = ch.getResponse(parser.parse(requestString)).getHeaders();

    assertEquals(expected, actual);

  }

  @Test
  public void CookieHandlerUsesCookieSentViaHeader() {
    String requestString = "GET /eat_cookie HTTP/1.1\r\nCookie: type=chocolate\r\n";
    CookieHandler ch = new CookieHandler();
    String expected = "mmmm chocolate";
    String actual = new String (ch.getResponse(parser.parse(requestString)).getBody());

    assertEquals(expected, actual);
  }

  @Test
  public void CookieHandlerUsesCookieWithMultipleHeaders() {
    String requestString = "GET /eat_cookie HTTP/1.1\r\nCookie: type=chocolate\r\nContent-type: text\r\n";
    CookieHandler ch = new CookieHandler();
    String expected = "mmmm chocolate";
    String actual = new String(ch.getResponse(parser.parse(requestString)).getBody());

    assertEquals(expected, actual);
  }

  @Test
  public void CookieHandlerWithNoHeadersAvoidsNPE() {
    String requestString = "GET /eat_cookie HTTP/1.1\r\nContent-type: text\r\n";
    CookieHandler ch = new CookieHandler();
    String expected = HTTPStatus.OK.getStatusLine();
    String actual = new String(ch.getResponse(parser.parse(requestString)).getStatusLine());

    assertEquals(expected, actual);
  }
}