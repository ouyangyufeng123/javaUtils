package com.yf.http;

/**
 * Created by ouyangyufeng on 2018/8/15.
 */

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 *
 * @author RAZR
 * @date 2017/9/18
 * post发送数据需要两种形式，如果接收的controller层使用实体接收，就需要进行以json格式发送，
 * 如果以单独字段进行接收，就需要进行键值对的形式发送
 */
@Component
public class HttpClient {

    /**
     * <p>获取http客户端</p>
     * @param url：请求地址
     * @param map：请求参数
     * @param method：请求方法
     * @return
     */
    public static JSONObject getHttpClient(String url, Map map,String method){
        String reqUrl = url;
        JSONObject paramJson=new JSONObject();
        if(map!=null) {
            for (int i = 0; i < map.size(); i++) {
                paramJson.putAll(map);
            }
        }
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = null;
        HttpGet httpGet = null;
        HttpResponse response = null;
        try {
            if(method.equals("POST")) {
                httpPost = new HttpPost(reqUrl);
                httpPost.addHeader("Content-Type","application/json; charset=utf-8");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setEntity(new StringEntity(paramJson.toString(), Charset.forName("UTF-8")));
                response = httpClient.execute(httpPost);
            }if(method.equals("GET")) {
                httpGet = new HttpGet(reqUrl);
                httpGet.addHeader("Content-Type","application/json; charset=utf-8");
                httpGet.setHeader("Accept", "application/json");
                response = httpClient.execute(httpGet);
            }
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed:" + response.getStatusLine());
            }else{
                String resultStr = EntityUtils.toString(response.getEntity());
                JSONObject resultJSON=JSONObject.parseObject(resultStr);
                return resultJSON;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}