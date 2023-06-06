package com.yancy.cursor.server.service.thread;

import com.yancy.cursor.server.client.ClientObject;
import com.yancy.cursor.server.service.ClientAddService;
import com.yancy.cursor.server.service.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author yancy0109
 */
public class AcceptThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(AcceptThread.class);

    private final Server server;

    private final ClientAddService clientService;

    /**
     * 阻塞获取链接时间
     */
    private static final int DELAY = 1000;

    public AcceptThread(Server server, ClientAddService clientService) {
        this.server = server;
        this.clientService = clientService;
    }

    @Override
    public void run() {
        try {
            while (true) {
                /**
                 * 定期接收连接
                 */
                Thread.sleep(DELAY);
                SocketChannel channel = this.server.connection();
                logger.info("Get connection from : {}", channel.getRemoteAddress().toString());
                clientService.addClient(new ClientObject(channel));
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
