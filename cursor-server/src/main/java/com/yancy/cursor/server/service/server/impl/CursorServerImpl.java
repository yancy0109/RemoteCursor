package com.yancy.cursor.server.service.server.impl;

import com.yancy.cursor.server.client.object.ClientObject;
import com.yancy.cursor.server.client.service.SelectService;
import com.yancy.cursor.server.client.service.impl.SelectServiceImpl;
import com.yancy.cursor.server.service.server.ClientAddService;
import com.yancy.cursor.server.service.server.CursorServer;
import com.yancy.cursor.server.service.server.Server;
import com.yancy.cursor.server.service.thread.AcceptThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author yancy0109
 */
public abstract class CursorServerImpl implements CursorServer, ClientAddService {

    private static final Logger logger = LoggerFactory.getLogger(CursorServer.class);

    private Server server;

    protected SelectService selectService = new SelectServiceImpl();


    public CursorServerImpl(String addr, int port) {
        this.server = new DefaultServer();
        try {
            server.listen(addr, port);
        } catch (IOException e) {
            throw new RuntimeException("Could not start Socket Server", e);
        }
    }

    @Override
    public void acceptConn() {
        /**
         * New Thread for Connection
         */
        Thread thread = new Thread(new AcceptThread(this.server, this));
        thread.start();
    }

    @Override
    public void run() {
        acceptConn();
    }

    @Override
    public void addClient(ClientObject clientObject) {
        selectService.addClient(clientObject);
    }

}
