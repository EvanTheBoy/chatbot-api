package com.evan.bot.chatbotapinewdomain.zsxq.model.vo;

import lombok.Data;

@Data
public class Topics {


    private String topicId;


    private Group group;


    private String type;


    private Question question;

    private boolean answered;


    private int likesCount;


    private int rewardsCount;


    private int commentsCount;


    private int readingCount;


    private int readersCount;

    private boolean digested;

    private boolean sticky;


    private String createTime;


    private UserSpecific userSpecific;

    public boolean getAnswered(){
        return this.answered;
    }

    public boolean getDigested(){
        return this.digested;
    }

    public boolean getSticky(){
        return this.sticky;
    }

}
