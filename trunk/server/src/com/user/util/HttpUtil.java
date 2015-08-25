package com.user.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.jackson.map.ObjectMapper;

public class HttpUtil {
	
	/**
	 * 发送HTTP请求
	 * @param url
	 * @param map
	 */
	public static Map<String, Object> send(String url, Map<String, Object> map){
		try {
			List<NameValuePair> pairList = new ArrayList<NameValuePair>();
			for(Entry<String, Object> entry : map.entrySet()) {
				pairList.add(new NameValuePair(entry.getKey(), entry.getValue().toString() ));
			}
			return convertResponse(doPostMethod(pairList, url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("v", 29540654);
		map.put("key", "keytest");
		map.put("value", "valueTest");
		send("http://localhost:8080/user/rest/save.jhtml", map);
	}
    
	/**
	 * 从返回值中解析出Map
	 * @param responseBody
	 * @return
	 */
	private static Map<String, Object> convertResponse(String responseBody) {
		ObjectMapper obj = new ObjectMapper();
		try {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = obj.readValue(responseBody, HashMap.class);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * 发送HTTP请求
	 * @param pairList
	 * @param restUrl
	 * @return
	 */
    private static String doPostMethod(List<NameValuePair> pairList , String restUrl) {
        NameValuePair[] pairs = pairList.toArray(new NameValuePair[pairList.size()]);
        PostMethod postMethod = new PostMethod(restUrl);
        // 将值放入postMethod中
        postMethod.setRequestBody(pairs);
        // 执行postMethod
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        //连接超时
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        //请求超时
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        try {
            httpClient.executeMethod(postMethod);
        } catch (HttpException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        String responseBody = "";       
        try {
            responseBody = new String(postMethod.getResponseBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace(); 
        } finally {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0);    
        }
        return responseBody;
    }
}
