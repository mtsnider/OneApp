package com.strangerandglory.one;

public class ChatMessage {
    private String name;
    private String message;

    public ChatMessage()
    {

    }

    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String result = String.format("%s\n%s", name, message);
        return result;
    }
}
