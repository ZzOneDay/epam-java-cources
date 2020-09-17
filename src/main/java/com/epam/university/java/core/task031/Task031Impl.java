package com.epam.university.java.core.task031;

public class Task031Impl implements Task031 {
    private Server server;

    @Override
    public Client createClient() {
        return new ClientImpl((ServerImpl) server);
    }

    @Override
    public Server createServer() {
        server = new ServerImpl();
        return server;
    }
}
