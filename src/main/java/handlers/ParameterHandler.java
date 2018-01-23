package handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import messages.Request;
import messages.Response;


public class ParameterHandler implements RequestHandler {
  private String parameterValues = "";

  public ParameterHandler (Request request) {

    if(request.hasParams()) {
      String[] paramList = request.getParams();
      for (String param : paramList){
        try {
          parameterValues += URLDecoder.decode(param.replace("=", " = "), "UTF-8");
          parameterValues += "\r\n";
        }catch (UnsupportedEncodingException e) {
          System.err.println("Parameter handler got an unsupported encoding type");
        }
      }
    }
  }

  public Response getResponse() {
    Response response = new Response();
    response.setStatusLine("HTTP/1.1 200 OK\r\n");
    response.setBody(parameterValues);

    return response;
  }
}