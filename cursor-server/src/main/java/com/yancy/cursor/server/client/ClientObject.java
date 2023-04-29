package com.yancy.cursor.server.client;

import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public class ClientObject {

    private String addr;

    private Socket socket;
    private SocketChannel socketChannel;

    public ClientObject(Socket socket) {
        this.addr = socket.getInetAddress().toString();
        this.socket = socket;
        this.socketChannel = socket.getChannel();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
