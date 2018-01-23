package messages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import parsers.RequestParser;

public class Request {

  private String httpMethod;
  private String rawUri;
  private String httpVersion;
  private String simpleUri;
  private String cookie;
  private String[] params;
  private HashMap<String, String> headers = new HashMap<>();

  public Request() {



  }

  /** public Request(String requestString) {
    this.setHttpMethod(RequestParser.getHttpMethod(requestString));
    this.setHttpVersion(RequestParser.getHttpVersion(requestString));
    this.setRawUri(RequestParser.getRawUri(requestString));
    this.setSimpleUri(RequestParser.getSimpleUri(requestString));

  } */

  public void setHeader(String key, String value) {
    this.headers.put(key, value);
  }

  public String getHeaderValue(String key) {
    return this.headers.get(key);
  }

  public String getRawUri() {
    return rawUri;
  }

  public void setRawUri(String rawUri) {
    this.rawUri = rawUri;
  }

  public String getHttpVersion() {
    return httpVersion;
  }

  public void setHttpVersion(String httpVersion) {
    this.httpVersion = httpVersion;
  }


  public String getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(String httpMethod) {
    this.httpMethod = httpMethod;
  }
/**
  public void setRequestParams(String requestParams) {
    if (hasCookies()) {
      setCookie(RequestParser.getParams(requestParams).split("=")[1]);
    }

    if (hasParams()) {
      setParams(RequestParser.getParams(requestParams));
    }
  }

 */

  public String getCookie() {
    return cookie;
  }

  public void setCookie(String cookie) {
    this.cookie = cookie;
  }

  public String[] getParams() {
    return params;
  }

  public void setParams(String[] params) {
    this.params = params;
  }

  public String getSimpleUri() {
    return simpleUri;
  }

  public void setSimpleUri(String uri) {
    this.simpleUri = uri;
  }

  public Boolean hasParams() {
    if (rawUri.contains("?")) {
      return true;
    }
    return false;
  }

  public Boolean hasCookies() {
    if (rawUri.contains("type")) {
      return true;
    }
    return false;
  }

}

