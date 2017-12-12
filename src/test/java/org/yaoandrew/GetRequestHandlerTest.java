package org.yaoandrew;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GetRequestHandlerTest {

    @Test
    public void GetRequestHandlerReturnsResponse() {
        Request request = new Request ("GET / HTTP/1.1\r\n");
        GetRequestHandler handleGet = new GetRequestHandler();

        assertEquals("GET / HTTP/1.1\r\n", handleGet.getResponse());


    }
}
