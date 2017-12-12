package org.yaoandrew;

public class PostRequestHandler implements RequestHandler {
    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");
        return response;
    }
}
