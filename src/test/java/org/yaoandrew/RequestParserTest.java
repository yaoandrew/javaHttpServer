package org.yaoandrew;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {

String testString = "GET /foobar HTTP/1.1";

    @Test
  public void ParserReturnsHTTPMethod() {
      assertEquals("GET", RequestParser.getHttpMethod(testString));
    }

    @Test
  public void ParserReturnsURI() {
      assertEquals("/foobar", RequestParser.getURI(testString));
    }

    @Test
  public void ParserReturnsHTTPVersion() {
      assertEquals("HTTP/1.1", RequestParser.getHttpVersion(testString));
    }
}