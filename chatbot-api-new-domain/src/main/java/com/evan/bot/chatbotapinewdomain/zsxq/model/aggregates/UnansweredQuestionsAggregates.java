package com.evan.bot.chatbotapinewdomain.zsxq.model.aggregates;

import com.evan.bot.chatbotapinewdomain.zsxq.model.res.RespData;
import lombok.Data;

@Data
public class UnansweredQuestionsAggregates {
    private boolean succeeded;

    private RespData respData;
}
