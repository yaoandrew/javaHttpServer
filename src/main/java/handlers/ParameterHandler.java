package handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import messages.Request;
import messages.Response;
import parsers.ParameterParser;


public class ParameterHandler implements RequestHandler {
  String parameterValues = "";

  public ParameterHandler (Request request) {

    if(request.hasParams()) {
      String rawParams = ParameterParser.parseUri(request.getRawUri());
      String[] paramList = ParameterParser.parseRawParams(rawParams);
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