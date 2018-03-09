package parsers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import messages.Request;

public class RequestParser {

  public Request parse(String rawRequest) {

    Request request = new Request();

    String[] requestParts = rawRequest.split("\r\n\r\n");
    List<String> statusLineAndHeaders = Arrays.asList(requestParts[0].split("\r\n"));

    if (hasBodyData(requestParts)) {
      request.setBody(requestParts[1]);
    }

    if (requestHasHeaders(statusLineAndHeaders)) {
      Iterator<String> iterator = statusLineAndHeaders.listIterator(1);
      while (iterator.hasNext()) {
        String[] head = iterator.next().split(":");
        request.setHeader(head[0].trim(), head[1].trim());
      }
    }

    request.setHttpMethod(statusLineAndHeaders.get(0).split("\\s")[0]);
    request.setRawUri(statusLineAndHeaders.get(0).split("\\s")[1]);
    request.setSimpleUri(statusLineAndHeaders.get(0).split("\\s")[1]);
    request.setHttpVersion(statusLineAndHeaders.get(0).split("\\s")[2]);

    if (request.hasParams()) {
      request.setSimpleUri(request.getRawUri().split("\\?")[0]);
      List<String> params = Arrays.asList(request.getRawUri().split("\\?")[1].split("&"));
      Iterator<String> iterator = params.listIterator();
      while (iterator.hasNext()) {
        String[] param = iterator.next().split("=");
        request.setParamValue(param[0].trim(), param[1].trim());
      }
    }

    return request;
  }

  private boolean requestHasHeaders(List<String> statusLineAndHeaders) {
    return statusLineAndHeaders.size() > 1;
  }

  private boolean hasBodyData(String[] requestParts) {
    return requestParts.length > 1;
  }

}