package messages;

public class Response {

    private String statusLine = "";
    private String headers = "";
    private byte[] body;
    private String newLine = "\r\n";


    public String getStatusLine() {
        return statusLine;
    }

    public String getHeaders() {
        return headers;
    }

    public String getSeparator() {
        return newLine + newLine;
    }

    public byte[] getBody() {
        return body;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
