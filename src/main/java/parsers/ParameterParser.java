package parsers;

public class ParameterParser {

  public static String parseUri (String requestUri) {
    return requestUri.split("\\?")[1];
  }

  public static String[] parseRawParams (String rawParams) {
    return rawParams.split("&");
  }
}
