import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import messages.Request;

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

}
