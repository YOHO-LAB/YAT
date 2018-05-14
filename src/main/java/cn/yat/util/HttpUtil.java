package cn.yat.util;

import cn.yat.myentity.RunHttpResultEntity;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpUtil {
    public static RunHttpResultEntity doPost(String url, String param, List<Cookie> inputCookies, List<Header> inputHeaders, int socketTimeout, int connectTimeout) throws Exception{
        RunHttpResultEntity oRunHttpResultEntity = new RunHttpResultEntity();
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        if(inputCookies != null){
            for(Cookie oCookie : inputCookies){
                cookieStore.addCookie(oCookie);
            }
        }
        List<NameValuePair> nameValuePairArrayList = new ArrayList<NameValuePair>();
        if(!param.equals("") && param.contains("=")){
            for(String str : param.split("&")){
                if(str.contains("=")){
                    int idx = str.indexOf("=");
                    String k = str.substring(0,idx);
                    String v = str.substring(idx+1,str.length());
                    nameValuePairArrayList.add(new BasicNameValuePair(k,v));
                }
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairArrayList, "utf-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        if(inputHeaders != null){
            for(Header oHeader : inputHeaders){
                httpPost.addHeader(oHeader);
            }
        }
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        long startTime =System.currentTimeMillis();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        if (httpEntity!=null) {
            String respStr = EntityUtils.toString(httpEntity, "UTF-8");
            oRunHttpResultEntity.setResponse(respStr);
        }
        EntityUtils.consume(httpEntity);
        long endTime =System.currentTimeMillis();
        oRunHttpResultEntity.setResponseTime(endTime-startTime);
        int httpCode = httpResponse.getStatusLine().getStatusCode();
        oRunHttpResultEntity.setCode(httpCode);
        List<Cookie> cookies = cookieStore.getCookies();
        oRunHttpResultEntity.setCookie(cookies);
        Header[] headers = httpResponse.getAllHeaders();
        oRunHttpResultEntity.setHeader(headers);
        long contentLength = httpEntity.getContentLength();
        oRunHttpResultEntity.setContentLength(contentLength);
        Header[] headersRequest = httpPost.getAllHeaders();
        oRunHttpResultEntity.setHeadersRequest(headersRequest);
        return oRunHttpResultEntity;
    }

    public static RunHttpResultEntity doGet(String url,String param,List<Cookie> inputCookies,List<Header> inputHeaders,int socketTimeout,int connectTimeout) throws Exception{
        RunHttpResultEntity oRunHttpResultEntity = new RunHttpResultEntity();
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        if(inputCookies != null){
            for(Cookie oCookie : inputCookies){
                cookieStore.addCookie(oCookie);
            }
        }
        StringBuilder urlStringBuilder=new StringBuilder(url);
        HttpGet httpGet=new HttpGet(urlStringBuilder.toString());
        if(inputHeaders != null){
            for(Header oHeader : inputHeaders){
                httpGet.addHeader(oHeader);
            }
        }
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
        httpGet.setConfig(requestConfig);
        long startTime =System.currentTimeMillis();
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity=httpResponse.getEntity();
        if (httpEntity!=null) {
            String respStr = EntityUtils.toString(httpEntity, "UTF-8");
            oRunHttpResultEntity.setResponse(respStr);
        }
        EntityUtils.consume(httpEntity);
        long endTime =System.currentTimeMillis();
        int httpCode = httpResponse.getStatusLine().getStatusCode();
        oRunHttpResultEntity.setCode(httpCode);
        List<Cookie> cookies = cookieStore.getCookies();
        oRunHttpResultEntity.setCookie(cookies);
        Header[] headers = httpResponse.getAllHeaders();
        oRunHttpResultEntity.setHeader(headers);
        oRunHttpResultEntity.setResponseTime(endTime-startTime);
        long contentLength = httpEntity.getContentLength();
        oRunHttpResultEntity.setContentLength(contentLength);
        Header[] headersRequest = httpGet.getAllHeaders();
        oRunHttpResultEntity.setHeadersRequest(headersRequest);
        return oRunHttpResultEntity;
    }

    public static String replaceIllegalChar(String url) throws Exception{
        String ret = url.trim();
        if(url.contains(" ")){
            ret = url.replaceAll(" ","%20");
        }
        return ret;
    }

}
