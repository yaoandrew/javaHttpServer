package parsers;

public class CookieParser {

  public static String parseUri(String rawUri){
   String[] rawCookie;
   rawCookie = rawUri.split("\\?");

   return rawCookie[1];
  }

  public static String parseRawCookie(String rawCookie){
    String cookieValue;
    cookieValue = rawCookie.split("=")[1];

    return cookieValue;
  }

}
