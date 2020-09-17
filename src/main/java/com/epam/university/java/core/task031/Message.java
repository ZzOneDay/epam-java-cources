package com.epam.university.java.core.task031;

class Message {
    private String message;
    private boolean isRead = false;

    Message(String message) {
        this.message = message;
    }

    String getMessage() {
        return message;
    }

    boolean isRead() {
        return isRead;
    }

    void wasRead() {
        isRead = true;
    }
}
