package messages;

public enum HTTPStatus {
  OK              ("HTTP/1.1 200 OK"),
  PARTIAL_CONTENT ("HTTP/1.1 206 Partial Content"),
  FOUND           ("HTTP/1.1 302 Found"),
  NOT_FOUND       ("HTTP/1.1 404 Not Found"),
  IM_A_TEAPOT     ("HTTP/1.1 418 I'm a Teapot");

  private final String statusLine;

  HTTPStatus(String statusLine) {
    this.statusLine = statusLine;
  }

  public String getStatusLine() {
    return this.statusLine;
  }

}