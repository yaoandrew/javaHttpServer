package org.yaoandrew;

import org.junit.Test;

public class GetRequestHandlerTest {

    @Test
    public void GetRequestHandlerReturnsResponse() {
        Request request = new Request ("GET / HTTP/1.1\r\n");
        GetRequestHandler handleGet = new GetRequestHandler(request);

        assertEqual (expected, handleGet.getResponse);


    }
}
