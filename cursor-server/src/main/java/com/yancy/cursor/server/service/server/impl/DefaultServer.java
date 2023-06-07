package com.yancy.cursor.server.service.server.impl;

import com.yancy.cursor.server.service.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public class DefaultServer implements Server {

    private static final Logger logger = LoggerFactory.getLogger(DefaultServer.class);

    protected ServerSocketChannel socketChannel = null;

    @Override
    public void listen(String addr, int port) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(addr, port);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(socketAddress);
        this.socketChannel = serverSocketChannel;
        logger.info("Start Socket Server...");
    }

    @Override
    public SocketChannel connection() {
        if (this.socketChannel == null) {
            throw new RuntimeException("The Server Socket did not init.");
        }
        SocketChannel accepted;
        try {
            accepted = this.socketChannel.accept();
        } catch (IOException e) {
            throw new RuntimeException("The Server Socket accept connection has error.", e);
        }
        return accepted;
    }
}
