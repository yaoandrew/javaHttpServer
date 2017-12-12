package org.yaoandrew;

public class PutRequestHandler implements RequestHandler {
    public Response getResponse() {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");

        return response;
    }
}
