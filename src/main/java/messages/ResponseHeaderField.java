package messages;

public enum ResponseHeaderField {
  CONTENT_TYPE  ("Content-Type: "),
  LOCATION      ("Location: "),
  ALLOW         ("Allow: "),
  SET_COOKIE    ("Set-Cookie: "),
  WWW_AUTH      ("WWW-Authenticate: ");

  private final String field;

  ResponseHeaderField(String field) {
    this.field = field;
  }

  public String getHeaderField() {
    return this.field;
  }
}
