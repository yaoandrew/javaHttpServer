package org.yaoandrew;

public class Request {

    private String httpMethod;
    private String uri;
    private String httpVersion;

    public Request (String requestString) {
        this.setHttpMethod(RequestParser.getHttpMethod(requestString));
        this.setHttpVersion(RequestParser.getHttpVersion(requestString));
        this.setResource(RequestParser.getURI(requestString));
    }

    public String getResource() {
        return uri;
    }
    public void setResource(String uri) {
        this.uri = uri;
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

}
