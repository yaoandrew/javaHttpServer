
import messages.Request;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import parsers.RequestParser;

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
}