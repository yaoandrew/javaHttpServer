package org.yaoandrew;

public class Response {

    private static String statusLine = "HTTP/1.1 200 OK\r\n";
    private static String headers = "Allow: GET,OPTIONS\r\n";
    private static String body = "";
    private static String newLine = "\r\n";


    static String getStatusLine() {
        return statusLine;
    }

    static String getHeaders() {
        return headers;
    }

    static String getSeperator() {
        return newLine + newLine;
    }

    static String getNewLine() {
        return newLine;
    }

    static String getBody() {
        return body;
    }
}
