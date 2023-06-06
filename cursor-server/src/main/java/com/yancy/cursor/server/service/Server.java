package com.yancy.cursor.server.service;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * 服务器类接口
 * @author yancy0109
 */
public interface Server {

    /**
     * 开启端口监听，允许建立远程连接
     */
    void listen(String addr, int port) throws IOException;



    /**
     * 接收链接，获取 Socket 对象
     */
    SocketChannel connection();

}
