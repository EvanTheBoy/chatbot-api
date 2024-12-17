package com.evan.bot.chatbotapinewdomain.zsxq;

import com.evan.bot.chatbotapinewdomain.zsxq.model.aggregates.UnansweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {
    UnansweredQuestionsAggregates queryUnansweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
