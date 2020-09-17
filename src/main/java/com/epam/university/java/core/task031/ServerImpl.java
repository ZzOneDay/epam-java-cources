package com.epam.university.java.core.task031;

import java.util.ArrayList;

public class ServerImpl implements Server {
    private ArrayList<Message> messages = new ArrayList<>();
    private boolean isWork;

    void addMessage(Message message) {
        if (!isWork) {
            throw new IllegalArgumentException();
        }
        messages.add(0, message);
    }

    @Override
    public String readMessage() {
        if (!isWork) {
            throw new IllegalArgumentException();
        }
        for (Message message : messages) {
            if (!message.isRead()) {
                message.wasRead();
                return message.getMessage();
            }
        }
        return "";
    }

    @Override
    public void start() {
        isWork = true;
    }

    @Override
    public void stop() {
        isWork = false;
    }
}
