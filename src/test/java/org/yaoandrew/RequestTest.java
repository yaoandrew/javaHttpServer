package org.yaoandrew;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RequestTest {

    String testString = "GET /foobar HTTP/1.1";
    Request request = new Request(testString);

    @Test
    public void RequestHttpMethodParsesIntoObject() {
        assertEquals("GET", request.getHttpMethod());
    }

}
