package com.evan.bot.chatbotapinewdomain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.evan.bot.chatbotapinewdomain.zsxq.IZsxqApi;
import com.evan.bot.chatbotapinewdomain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import com.evan.bot.chatbotapinewdomain.zsxq.model.req.AnswerReq;
import com.evan.bot.chatbotapinewdomain.zsxq.model.req.ReqData;
import com.evan.bot.chatbotapinewdomain.zsxq.model.res.AnswerRes;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnansweredQuestionsAggregates queryUnansweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=unanswered_questions&count=20");
        httpGet.addHeader("cookie", cookie);
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("result for extracting the question: groupId:{}, jsonStr:{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnansweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("Error code is: "+ response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/answer");
        httpPost.addHeader("cookie", cookie);
        httpPost.addHeader("Content-Type", "application/json;charset=utf8");
        httpPost.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0");

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSONObject.fromObject(answerReq).toString();

        StringEntity stringEntity = new StringEntity(paramJson,
                ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(execute.getEntity());
            logger.info("result for answering the question: groupId:{}, topicId:{}, jsonStr:{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("Error code is: "+ execute.getStatusLine().getStatusCode());
        }
    }
}
