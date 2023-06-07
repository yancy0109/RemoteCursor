package com.yancy.cursor.server.client.object;

import java.io.IOException;

/**
 * 客户端对象接口
 * @author yancy0109
 */
public interface Client {

    /**
     * 向目标 Client 对象
     */
    void receive(byte[] positions) throws IOException;
    
}
