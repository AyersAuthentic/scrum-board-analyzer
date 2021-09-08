package main.java.memoranda.api;

public class RequestConfig {

    private String params;
    private String body;
    private String queryString;

    public RequestConfig(){}

    public String getParams() {
        return params;
    }

    public String getBody() {
        return body;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
