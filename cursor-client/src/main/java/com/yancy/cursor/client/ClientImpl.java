package com.yancy.cursor.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public class ClientImpl implements Client {
    @Override
    public byte[] read() {
        return new byte[0];
    }

    @Override
    public void connServer(String addr, int port) {
        Socket socket = new Socket();
        InetSocketAddress socketAddress = new InetSocketAddress(addr, port);
        try {
            socket.connect(socketAddress);
            SocketChannel channel = socket.getChannel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ClientImpl().connServer("127.0.0.1", 10002);
    }
}
