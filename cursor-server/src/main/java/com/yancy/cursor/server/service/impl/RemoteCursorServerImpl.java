package com.yancy.cursor.server.service.impl;

import com.yancy.cursor.server.client.ClientObject;
import com.yancy.cursor.server.service.ClientService;
import com.yancy.cursor.server.service.RemoteCursorServer;
import com.yancy.cursor.server.service.Server;
import com.yancy.cursor.server.service.thread.AcceptThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yancy0109
 */
public class RemoteCursorServerImpl implements RemoteCursorServer, ClientService {

    private Server server;

    private List<ClientObject> clientList = new ArrayList<>();

    public RemoteCursorServerImpl(String addr, int port) {
        this.server = new DefaultServer();
        try {
            server.listen(addr, port);
        } catch (IOException e) {
            throw new RuntimeException("Could not start Socket Server", e);
        }
    }

    @Override
    public void acceptConn() {
        Thread thread = new Thread(new AcceptThread(this.server, this));
        thread.start();
    }

    @Override
    public void run() {
        acceptConn();
    }

    @Override
    public void addClient(ClientObject clientObject) {
        this.clientList.add(clientObject);
    }

    @Override
    public void closeClient() {
        for (ClientObject client : clientList) {
            try {
                client.getSocket().close();
            } catch (IOException e) {
                throw new RuntimeException("Cloud not close Socket Connect", e);
            }
        }

    }
}
