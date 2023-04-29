package com.yancy.cursor.server.service;

/**
 * @author yancy0109
 */
public interface RemoteCursorServer {

    /**
     * 开启线程接收连接对象
     */
    void acceptConn();

    /**
     * 启动接口
     */
    void run();

}
