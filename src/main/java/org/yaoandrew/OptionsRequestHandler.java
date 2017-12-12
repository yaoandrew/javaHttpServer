package org.yaoandrew;

public class OptionsRequestHandler implements RequestHandler {
    String route;

    public OptionsRequestHandler (String route){
        this.route = route;
    }

    public Response getResponse() {
        Response response = new Response();
        response.setStatusLine("HTTP/1.1 200 OK\r\n");

        if (route.equals("/method_options")){
            response.setHeaders("Allow: GET,HEAD,POST,OPTIONS,PUT\r\n");
        } else {
            response.setHeaders("Allow: GET,OPTIONS\r\n");
        }

        return response;
    }
}
