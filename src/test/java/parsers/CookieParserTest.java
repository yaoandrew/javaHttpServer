package parsers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CookieParserTest {

  String testUri = "/cookie?type=chocolate";

  @Test
  public void ParserReturnsRawCookies() {
    String expected = "type=chocolate";
    String actual = CookieParser.parseUri(testUri);

    assertEquals(expected, actual);
  }

  @Test
  public void ParseRawCookieReturnsCookieValue() {
    String expected = "chocolate";
    String actual = CookieParser.parseRawCookie("type=chocolate");

    assertEquals(expected, actual);


  }

}
