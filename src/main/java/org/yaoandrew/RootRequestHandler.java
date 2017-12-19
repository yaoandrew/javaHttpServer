package org.yaoandrew;

public class RootRequestHandler implements RequestHandler{
    private String[] supportedHttpMethods;

    public RootRequestHandler (String[] supportedHttpMethods){
        this.supportedHttpMethods = supportedHttpMethods;
    }

    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");

        return response;
    }
}
