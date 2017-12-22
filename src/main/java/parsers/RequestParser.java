package parsers;

public class RequestParser {


  public static String getHttpMethod(String requestString) {
    return requestString.split("\\s")[0];
  }

  public static String getURI(String requestString) {
    return requestString.split("\\s")[1];
  }

  public static String getHttpVersion(String requestString) {
    return requestString.split("\\s")[2];
  }
}