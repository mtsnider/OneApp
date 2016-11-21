package com.strangerandglory.one;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by m on 2016-11-14.
 */


@IgnoreExtraProperties
public class Member {
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private boolean is_active;
    private int phone;
    private String uid;
    private List<Conversation> conversations;
    private Conversation conversation_id;

    public Member(String email, String first_name){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean is_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Conversation> getConversation_ids() {
        return conversations;
    }

    public void addConversation(Conversation conversation_id){
        conversations.add(conversation_id);
    }

}
