package com.etong.sms.utility;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * httpClient 4.5
 * http://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html
 * Created by zhenghuasheng on 2016/12/14.15:55
 */
public class HttpClientUtil {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://localhost:8080/sms/userinfo");
        httpPost.addHeader("Content-Type", "application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "zhs");
        jsonObject.put("password", "123456");

        httpPost.setEntity(new StringEntity(jsonObject.toJSONString(), Charsets.UTF_8));

        CloseableHttpResponse response = httpclient.execute(httpPost);
        int code = response.getStatusLine().getStatusCode();

        if (HttpStatus.SC_OK == code) {
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));

            EntityUtils.consume(entity);
        }

        response.close();

    }


}
