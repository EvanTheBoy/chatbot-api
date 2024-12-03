package com.evan.bot.chatbotapiinterface;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ChatbotApiInterfaceApplicationTests {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/15555858884212/topics?scope=unanswered_questions&count=20");
        httpGet.addHeader("cookie", "sajssdk_2015_cross_new_user=1; zsxqsessionid=e313ec8c37a4014173c15af009599957; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212212458845551%22%2C%22first_id%22%3A%2219382ae87113ff-04f3c78852982-7e433c49-1484784-19382ae8712318e%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkzODJhZTg3MTEzZmYtMDRmM2M3ODg1Mjk4Mi03ZTQzM2M0OS0xNDg0Nzg0LTE5MzgyYWU4NzEyMzE4ZSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjIxMjIxMjQ1ODg0NTU1MSJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212212458845551%22%7D%7D; zsxq_access_token=A7F23187-5D22-1495-6A1A-E5A8DBB5F440_F06631CB85811F80");
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseEntity = EntityUtils.toString(response.getEntity());
            System.out.println(responseEntity);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer_question() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/1525128511518582/answer");
        httpPost.addHeader("cookie", "zsxq_access_token=A7F23187-5D22-1495-6A1A-E5A8DBB5F440_F06631CB85811F80; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22212212458845551%22%2C%22first_id%22%3A%2219382ae87113ff-04f3c78852982-7e433c49-1484784-19382ae8712318e%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkzODJhZTg3MTEzZmYtMDRmM2M3ODg1Mjk4Mi03ZTQzM2M0OS0xNDg0Nzg0LTE5MzgyYWU4NzEyMzE4ZSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjIxMjIxMjQ1ODg0NTU1MSJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22212212458845551%22%7D%2C%22%24device_id%22%3A%2219387f14a9d1eb8-0bc6c28bc399758-7e433c49-1484784-19387f14a9e1b79%22%7D; zsxqsessionid=802cb067b0c8796a34efab203c3a18c4");
        httpPost.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"Again, ask ChatGPT.\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": true\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson,
                ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        if (execute.getStatusLine().getStatusCode() == 200) {
            String result = EntityUtils.toString(execute.getEntity());
            System.out.println(result);
        } else {
            System.out.println(execute.getStatusLine().getStatusCode());
        }
    }

    @Test
    void contextLoads() {
    }

}
