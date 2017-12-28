package messages;

import parsers.RequestParser;

public class Request {

    private String httpMethod;
    private String rawUri;
    private String httpVersion;
    private String cookie;
    private String params;

    public Request (String requestString) {
        this.setHttpMethod(RequestParser.getHttpMethod(requestString));
        this.setHttpVersion(RequestParser.getHttpVersion(requestString));
        this.setRawUri(RequestParser.getRawUri(requestString));

        setRequestParams(requestString);
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

    public void setRequestParams(String requestParams) {
      if (hasCookies()) {
        setCookie(RequestParser.getParams(requestParams));
      }

      if (hasParams()) {
        setParams(RequestParser.getParams(requestParams));
      }
    }

    public String getCookie() {
      return cookie;
    }

    public void setCookie(String cookie) {
      this.cookie = cookie;
    }

    public String getParams() {
      return params;
    }

    public void setParams(String params) {
      this.params = params;
    }

    public Boolean hasParams() {
      if (rawUri.contains("?")){
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
