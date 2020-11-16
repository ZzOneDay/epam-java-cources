package com.epam.university.java.core.task031;

public class ClientImpl implements Client {
    private ServerImpl server;
    private boolean isWork;

    ClientImpl(ServerImpl server) {
        this.server = server;
    }

    @Override
    public void sendMessage(String message) {
        if (message == null) {
            throw new IllegalArgumentException();
        }

        if (!isWork) {
            throw new IllegalArgumentException();
        }
        Message messageForSend = new Message(message);
        server.addMessage(messageForSend);
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
