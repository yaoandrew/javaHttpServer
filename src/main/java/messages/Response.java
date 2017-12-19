package messages;

public class Response {

    private String statusLine = "";
    private String headers = "";
    private String body = "";
    private String newLine = "\r\n";


    public String getStatusLine() {
        return statusLine;
    }

    public String getHeaders() {
        return headers;
    }

    public String getSeperator() {
        return newLine + newLine;
    }

    public String getNewLine() {
        return newLine;
    }

    public String getBody() {
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
