package handlers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import messages.HTTPStatus;
import messages.Request;
import messages.Response;


public class ParameterHandler extends RequestHandler {

  private String parameterValues = "";

  public Response getResponse(Request request) {
    if (request.hasParams()) {
      for (Map.Entry<String, String> entry : request.getParamMap().entrySet()) {
        try {
          parameterValues += URLDecoder.decode(entry.getKey() + " = " + entry.getValue(), "UTF-8");
          parameterValues += "\r\n";
        } catch (UnsupportedEncodingException e) {
          System.err.println("Parameter handler got an unsupported encoding type");
        }
      }
    }
    response.setStatusLine(HTTPStatus.OK.getStatusLine());
    response.setBody(parameterValues.getBytes());

    return response;
  }
}