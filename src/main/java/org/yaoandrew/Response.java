package org.yaoandrew;

public class Response {

    private String statusLine = "HTTP/1.1 200 OK\r\n";
    private String headers = "Allow: GET,OPTIONS\r\n";
    private String body = "";
    private String newLine = "\r\n";


    String getStatusLine() {
        return statusLine;
    }

    String getHeaders() {
        return headers;
    }

    String getSeperator() {
        return newLine + newLine;
    }

    String getNewLine() {
        return newLine;
    }

    String getBody() {
        return body;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }
}
