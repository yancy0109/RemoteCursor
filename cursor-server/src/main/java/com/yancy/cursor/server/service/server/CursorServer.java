package com.yancy.cursor.server.service.server;

/**
 * @author yancy0109
 */
public interface CursorServer {

    /**
     * 开启线程接收连接对象
     */
    void acceptConn();

    /**
     * 启动接口
     */
    void run();

}
