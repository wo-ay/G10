package com.coursedemo.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CourseScheduleController {

    public static String login_url = "http://ca.zucc.edu.cn/cas/login?service=http%3A%2F%2Fstudent.zucc.edu.cn%2Findex.portal";
    //public static String login_url = "http://student.zucc.edu.cn";

    public void CrawlerZUCC() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = new HttpPost(login_url);
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("authType", "0"));
        formParams.add(new BasicNameValuePair("username", "31901245"));
        formParams.add(new BasicNameValuePair("password", "a13777888199"));
        formParams.add(new BasicNameValuePair("It", ""));
        formParams.add(new BasicNameValuePair("execution", "e1s1"));
        formParams.add(new BasicNameValuePair("_eventId", "submit"));
        formParams.add(new BasicNameValuePair("submit", ""));
        formParams.add(new BasicNameValuePair("randomStr", ""));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");//将表单参数转化为“实体”
        post.setEntity(entity);//将“实体“设置到POST请求里
        HttpResponse response = httpClient.execute(post);//提交POST请求
        //if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
            String content = EntityUtils.toString(result);//用httpcore.jar提供的工具类将"实体"转化为字符串打印到控制台
            System.out.println(content);
        //}

    }

    public static void main(String[] args) throws IOException {
        new CourseScheduleController().CrawlerZUCC();
    }
}
