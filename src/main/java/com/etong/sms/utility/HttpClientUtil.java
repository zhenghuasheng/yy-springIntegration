package com.etong.sms.utility;

import java.io.IOException;

/**
 * httpClient 4.5
 * http://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html
 * Created by zhenghuasheng on 2016/12/14.15:55
 */
public class HttpClientUtil {

    public static void main(String[] args) throws IOException {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        HttpPost httpPost = new HttpPost("http://localhost:8080/sms/userinfo");
//        httpPost.addHeader("Content-Type", "application/json");
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("username", "zhs");
//        jsonObject.put("password", "123456");
//
//        httpPost.setEntity(new StringEntity(jsonObject.toJSONString(), Charsets.UTF_8));
//
//        CloseableHttpResponse response = httpclient.execute(httpPost);
//        int code = response.getStatusLine().getStatusCode();
//
//        if (HttpStatus.SC_OK == code) {
//            HttpEntity entity = response.getEntity();
//            System.out.println(EntityUtils.toString(entity));
//
//            EntityUtils.consume(entity);
//        }
//
//        response.close();

        new Thread(() -> test(10)).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                test(10);
//            }
//        }).start();



    }


    public static void test(int num){
        for (int i = 0 ; i< num; i++)
        System.out.println(i);
    }


}
