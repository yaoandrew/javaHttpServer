package parsers;

public class RequestParser {

  public static String getHttpMethod(String requestString) {
    return requestString.split("\\s")[0];
  }

  public static String getURI(String requestString) {
    return requestString.split("\\s")[1].split("\\?")[0];
  }

  public static String getRawUri(String requestString) {
    return requestString.split("\\s")[1];
  }

  public static String getHttpVersion(String requestString) {
    return requestString.split("\\s")[2];
  }

  public static String getParams(String requestString) {
    return requestString.split("\\s")[1].split("\\?")[1];
  }
  public static String getSimpleUri(String requestString) {
    return requestString.split("\\s")[1].split("\\?")[0];
  }
  public static String[] getStatusHeaders(String requestString) {
      return requestString.split("\r\n");
  }
}