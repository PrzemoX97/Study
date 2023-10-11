package com.EmailClient.model;

import java.io.Serializable;

public class UserData implements Serializable {

    public String recipient;
    public String title;
    public String content;

    public UserData() {
    }

    public UserData(final String recipient,
                    final String title,
                    final String content) {

        this.recipient = recipient;
        this.title = title;
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return content;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "recipient='" + recipient + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
