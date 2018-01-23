package parsers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import messages.Request;

public class RequestParser {

  public Request parse(String rawRequest) {

    String bodyData;
    Request request = new Request();

    String[] requestParts = rawRequest.split("\r\n\r\n");
    List <String>statusLineParts = Arrays.asList(requestParts[0].split("\r\n"));

    //has body info
    if (requestParts.length > 2) {
      bodyData = requestParts[1];
    }

    //has headers
    if (statusLineParts.size() > 1) {
      Iterator<String> iterator = statusLineParts.listIterator(1);
      while (iterator.hasNext()) {
        String[] head = iterator.next().split(":");
        request.setHeader(head[0].trim(), head[1].trim());
      }
    }

    request.setHttpMethod(statusLineParts.get(0).split("\\s")[0]);
    request.setRawUri(statusLineParts.get(0).split("\\s")[1]);
    request.setSimpleUri(statusLineParts.get(0).split("\\s")[1]);
    request.setHttpVersion(statusLineParts.get(0).split("\\s")[2]);

    if (request.hasParams()) {
      request.setSimpleUri(request.getRawUri().split("\\?")[0]);
      String[] params = request.getRawUri().split("\\?")[1].split("&");
      request.setParams(params);
    }

    if (request.hasCookies()) {
      request.setCookie(request.getRawUri().split("\\?")[1].split("=")[1]);
    }

    return request;
  }
}