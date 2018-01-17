package messages;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class RequestTest {

    String testString = "GET /foobar HTTP/1.1";
    Request request = new Request(testString);

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
        Request request = new Request(cookieUri);

        assertTrue(request.hasCookies());
    }

    @Test
    public void RequestHasCookiesReturnsCoookieValue() {
        String cookieUri = "GET /cookie?type=vanilla HTTP/1.1";
        Request request = new Request(cookieUri);

        assertEquals("vanilla", request.getCookie());

    }

    @Test
    public void RequestHasParamsReturnsTrue() {
        String paramUri = "GET /params?variable1=abc HTTP/1.1";
        Request request = new Request(paramUri);

        assertTrue(request.hasParams());
    }

    @Test
    public void RequestHasParamsReturnsParamValue() {
        String paramUri = "GET /params?variable1=abc HTTP/1.1";
        Request request = new Request(paramUri);

        assertEquals("variable1=abc", request.getParams());
    }

    @Test
    public void RequestWithParamsReturnsSimpleURI() {
        String paramUri = "GET /parameters?variable1=abc HTTP/1.1";
        Request request = new Request(paramUri);

        assertEquals("/parameters", request.getSimpleUri());
    }

    @Test
    public void RequestWithoutParamsReturnsSimpleURI() {
        String uri = "GET /hello HTTP/1.1";
        Request request = new Request(uri);

        assertEquals("/hello", request.getSimpleUri());
    }

    @Test
    public void RequestObjectSetsHeaderString() {
        String rawRequest = "GET /hello HTTP/1.1\r\nHello: World\r\nContent: text\r\n\r\nbody";
        String[] expectedHeader = {"Hello: World", "Content: text"};
        Request request = new Request(rawRequest);

        assertEquals(expectedHeader, request.getRawHeaders());
    }
}
