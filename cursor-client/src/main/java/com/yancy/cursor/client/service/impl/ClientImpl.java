package com.yancy.cursor.client.service.impl;

import com.yancy.cursor.client.service.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public abstract class ClientImpl implements Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    protected SocketChannel socketChannel;

    @Override
    public byte[] read() {
        if (this.socketChannel == null) {
//            final int MAX_WAIT = 10;
//            int time = 0;
//            while (time++ < MAX_WAIT) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            throw new RuntimeException("The Socket Channel has not init.");
        }
        ByteBuffer buffer = ByteBuffer.allocate(5);
        int bytesRead = 0;
        while (bytesRead < buffer.capacity()) {
            int result;
            try {
                result = this.socketChannel.read(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (result == -1) {
                // 已经到达流的末尾，可能需要关闭连接等操作
                break;
            }
            bytesRead += result;
        }
        return buffer.array(); // 读取完成后，可以通过调用 ByteBuffer.array() 方法获取 byte 数组
    }

    @Override
    public void connServer(String addr, int port) {
        InetSocketAddress socketAddress = new InetSocketAddress(addr, port);
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(socketAddress);
            this.socketChannel = socketChannel;
        } catch (IOException e) {
            logger.info("Socket Channel has init");
            throw new RuntimeException(e);
        }
    }

}
