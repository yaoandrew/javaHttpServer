package org.yaoandrew;

public class Response {

    private String statusLine = "";
    private String headers = "";
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

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public void setbody(String body) {
        this.body = body;
    }
}
