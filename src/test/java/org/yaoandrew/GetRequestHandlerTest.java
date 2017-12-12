package org.yaoandrew;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GetRequestHandlerTest {

    @Test
    public void GetRequestHandlerReturnsResponseWithStatus200() {
        String expected = "HTTP/1.1 200 OK\r\n";
        GetRequestHandler handleGet = new GetRequestHandler(true);

        assertEquals(expected, handleGet.getResponse().getStatusLine());
    }

    @Test
    public void GetRequestHandlerReturnsResponseWithStatus404() {
        String expected = "HTTP/1.1 404 Not Found\r\n";
        GetRequestHandler handleGet = new GetRequestHandler(false);

        assertEquals(expected, handleGet.getResponse().getStatusLine());
    }
}
