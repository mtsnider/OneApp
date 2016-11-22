package com.strangerandglory.one;

/**
 * Created by m on 2016-11-14.
 */

public class Message {
    private String uid;
    private String body;
    private Member sender_id;
    private String sent_timestamp;

    public Message(){}

    public String getId() {
        return uid;
    }

    public void setId(String id) {
        this.uid = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Member getSender() {
        return sender_id;
    }

    public void setSender(Member sender_id) {
        this.sender_id = sender_id;
    }

    public String getSent_timestamp() {
        return sent_timestamp;
    }

    public void setSent_timestamp(String sent_timestamp) {
        this.sent_timestamp = sent_timestamp;
    }
}


