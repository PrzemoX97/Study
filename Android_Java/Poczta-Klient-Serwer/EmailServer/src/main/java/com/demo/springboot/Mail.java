package com.demo.springboot;


public class Mail {
        private int id;
        private String recipient;
        private String title;
        private String content;
        public Mail(){}

        public Mail(int id, String recipient, String title, String content) {
            this.recipient = recipient;
            this.title = title;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public String getTo() {
            return recipient;
        }

        public String getSubject() {
            return title;
        }

        public String getBody() {
            return content;
        }

        public void setId(int id) { this.id = id; }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", recipient='" + recipient + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
