package messages;

public enum HTTPStatus {
  OK                    ("HTTP/1.1 200 OK"),
  NO_CONTENT            ("HTTP/1.1 204 No Content"),
  PARTIAL_CONTENT       ("HTTP/1.1 206 Partial Content"),
  FOUND                 ("HTTP/1.1 302 Found"),
  UNAUTHORIZED          ("HTTP/1.1 401 Unauthorized"),
  NOT_FOUND             ("HTTP/1.1 404 Not Found"),
  NOT_ALLOWED           ("HTTP/1.1 405 Method Not Allowed"),
  RANGE_NOT_SATISFIABLE ("HTTP/1.1 416 Range Not Satisfiable"),
  IM_A_TEAPOT           ("HTTP/1.1 418 I'm a Teapot");

  private final String statusLine;

  HTTPStatus(String statusLine) {
    this.statusLine = statusLine;
  }

  public String getStatusLine() {
    return this.statusLine;
  }

}