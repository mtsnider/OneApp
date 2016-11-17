package com.strangerandglory.one;

import java.util.List;

/**
 * Created by m on 2016-11-14.
 */

public class Conversation {
    private String id;
    private Member member_id;
    private Message message_id;
    private List<Message> messages;
    private List<Member> members;

    public Conversation(String id, List<Member> members, List<Message> messages){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMembers() {
        return member_id;
    }

    public void setMembers(Member members) {
        this.member_id = member_id;
    }

    public Message getMessages() {
        return message_id;
    }

    public void addMessage(Message message_id){
        messages.add(message_id);
    }

    public void addMember(Member member_id){
        members.add(member_id);
    }

    public void setMessages(Message message_id) {
        this.message_id = message_id;
    }
}
