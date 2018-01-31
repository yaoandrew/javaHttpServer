package messages;

import org.junit.Ignore;
import org.junit.Test;
import parsers.RequestParser;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RequestTest {

    String testString = "GET /foobar HTTP/1.1";
    RequestParser parser = new RequestParser();

    Request request = parser.parse(testString);

    @Test
    public void RequestHttpMethodParsesIntoObject() {
        assertEquals("GET", request.getHttpMethod());
    }

    @Test
    public void RequestURIParsesIntoObject() {
        assertEquals("/foobar", request.getRawUri());
    }

    @Test
    public void RequestVersionParsesIntoObject() {
        assertEquals("HTTP/1.1", request.getHttpVersion());
    }

    @Test
    public void RequestHasCookiesReturnsTrue() {
        String cookieUri = "GET /cookie?type=vanilla HTTP/1.1";
        Request request = parser.parse(cookieUri);

        assertTrue(request.getParamValue("type").equals("vanilla") );
    }

    @Test
    public void RequestHasCookiesReturnsCookieValue() {
        String cookieUri = "GET /cookie?type=vanilla HTTP/1.1";
        Request request = parser.parse(cookieUri);

        assertEquals("vanilla", request.getParamValue("type"));

    }

    @Test
    public void RequestHasParamsReturnsTrue() {
        String paramUri = "GET /params?variable1=abc HTTP/1.1";
        Request request = parser.parse(paramUri);

        assertTrue(request.hasParams());
    }

    @Test
    public void RequestHasParamsReturnsParamValue() {
        String paramUri = "GET /params?variable1=abc HTTP/1.1";
        Request request = parser.parse(paramUri);
        String expected = "abc";

        assertEquals(expected, request.getParamValue("variable1"));
    }

    @Test
    public void RequestWithParamsReturnsSimpleURI() {
        String paramUri = "GET /parameters?variable1=abc HTTP/1.1";
        Request request = parser.parse(paramUri);

        assertEquals("/parameters", request.getSimpleUri());
    }

    @Test
    public void RequestWithoutParamsReturnsSimpleURI() {
        String uri = "GET /hello HTTP/1.1";
        Request request = parser.parse(uri);

        assertEquals("/hello", request.getSimpleUri());
    }

}
