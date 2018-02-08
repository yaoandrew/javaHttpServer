package handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import messages.Request;
import messages.Response;


public class ParameterHandler implements RequestHandler {
  private String parameterValues = "";


  public Response getResponse(Request request) {
    Response response = new Response();
    if(request.hasParams()) {
      for (Map.Entry<String, String> entry : request.getParamMap().entrySet()){
        try {
          parameterValues += URLDecoder.decode(entry.getKey() + " = " + entry.getValue(), "UTF-8");
          parameterValues += "\r\n";
        }catch (UnsupportedEncodingException e) {
          System.err.println("Parameter handler got an unsupported encoding type");
        }
      }
    }
    response.setStatusLine("HTTP/1.1 200 OK\r\n");
    response.setBody(parameterValues.getBytes());

    return response;
  }
}