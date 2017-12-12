package org.yaoandrew;

public class GetRequestHandler {

  Response response;

    public String getResponse() {
        response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");

       return response.getStatusLine();
    }
}
