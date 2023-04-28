package com.yancy.cursor.server.server.impl;

import com.yancy.cursor.server.server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

/**
 * @author yancy0109
 */
public abstract class DefaultServer implements Server {

    @Override
    public ServerSocket listen(String addr, int port) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(addr, port);
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.bind(socketAddress);
        return serverSocket;
    }


}
