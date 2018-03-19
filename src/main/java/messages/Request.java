package messages;

import java.util.HashMap;

public class Request {

  private String httpMethod;
  private String rawUri;
  private String httpVersion;
  private String simpleUri;
  private String body;
  HashMap<String, String> params = new HashMap<>();
  HashMap<String, String> headers = new HashMap<>();

  public void setHeader(String key, String value) {
    this.headers.put(key, value);
  }

  public String getHeaderValue(String key) {
    return this.headers.get(key);
  }

  public HashMap<String, String> getHeadersMap() {
    return this.headers;
  }

  public String getRawUri() {
    return rawUri;
  }

  public void setRawUri(String rawUri) {
    this.rawUri = rawUri;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
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

  public String getParamValue(String key) {
    return this.params.get(key);
  }

  public HashMap<String, String> getParamMap() {
    return this.params;
  }

  public void setParamValue(String key, String value) {
    this.params.put(key, value);
  }

  public String getSimpleUri() {
    return simpleUri;
  }

  public void setSimpleUri(String uri) {
    this.simpleUri = uri;
  }

  public boolean hasParams() {
    return rawUri.contains("?");
  }
}

