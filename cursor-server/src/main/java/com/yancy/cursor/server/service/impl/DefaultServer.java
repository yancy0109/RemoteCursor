package com.yancy.cursor.server.service.impl;

import com.yancy.cursor.server.service.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author yancy0109
 */
public class DefaultServer implements Server {

    private static final Logger logger = LoggerFactory.getLogger(DefaultServer.class);

    protected ServerSocket serverSocket = null;

    @Override
    public void listen(String addr, int port) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(addr, port);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(socketAddress);
        this.serverSocket = serverSocket;
        logger.info("Start Socket Server...");
    }

    @Override
    public Socket connection() {
        if (this.serverSocket == null) {
            throw new RuntimeException("The Server Socket did not init.");
        }
        Socket accepted = null;
        try {
            accepted = this.serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException("The Server Socket accept connection has error.", e);
        }
        return accepted;
    }
}
