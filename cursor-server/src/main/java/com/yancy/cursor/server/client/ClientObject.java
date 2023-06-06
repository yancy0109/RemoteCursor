package com.yancy.cursor.server.client;

import com.yancy.cursor.server.client.service.ClientService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public class ClientObject implements ClientService {

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
    public void writeAction(ByteBuffer src) {
        try {
            this.getSocketChannel().write(src);
        } catch (IOException e) {
            throw new RuntimeException("There has a error when send Mouse Moved Msg. Caused by IOException", e);
        }
    }

    @Override
    public String toString() {
        return "ClientObject{" +
                "addr='" + addr + '\'' +
                ", socketChannel=" + socketChannel +
                '}';
    }
}
