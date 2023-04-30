package com.yancy.cursor.server.client;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public class ClientObject {

    private String addr;
    private SocketChannel socketChannel;

    public ClientObject(SocketChannel channel) {
        try {
            this.addr = channel.getRemoteAddress().toString();
            this.socketChannel = channel;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public String toString() {
        return "ClientObject{" +
                "addr='" + addr + '\'' +
                ", socketChannel=" + socketChannel +
                '}';
    }
}
