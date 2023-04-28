package com.yancy.cursor.server.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 服务器类接口
 * @author yancy0109
 */
public interface Server {

    /**
     * 开启端口监听，允许建立远程连接
     */
    ServerSocket listen(String addr, int port) throws IOException;


}
