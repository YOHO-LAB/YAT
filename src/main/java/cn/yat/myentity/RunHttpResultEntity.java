package cn.yat.myentity;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;

import java.util.List;

public class RunHttpResultEntity {
    long responseTime;
    String response;
    int code;
    List<Cookie> cookie;
    Header[] header;
    long contentLength;
    Header[] headersRequest;
    long totalTime;
    boolean pass;
    boolean skip;
    String uuid;
    String exception;

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Cookie> getCookie() {
        return cookie;
    }

    public void setCookie(List<Cookie> cookie) {
        this.cookie = cookie;
    }

    public Header[] getHeader() {
        return header;
    }

    public void setHeader(Header[] header) {
        this.header = header;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public Header[] getHeadersRequest() {
        return headersRequest;
    }

    public void setHeadersRequest(Header[] headersRequest) {
        this.headersRequest = headersRequest;
    }
}
