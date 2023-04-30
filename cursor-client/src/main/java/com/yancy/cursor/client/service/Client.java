package com.yancy.cursor.client.service;

import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public interface Client {

    void connServer(String addr, int port);

    /**
     * 读取单次指令
     */
    byte[] read();
}
