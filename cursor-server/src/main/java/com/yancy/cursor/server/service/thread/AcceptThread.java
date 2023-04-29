package com.yancy.cursor.server.service.thread;

import com.yancy.cursor.server.client.ClientObject;
import com.yancy.cursor.server.service.ClientService;
import com.yancy.cursor.server.service.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

/**
 * @author yancy0109
 */
public class AcceptThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(AcceptThread.class);

    private final Server server;

    private final ClientService clientService;

    /**
     * 阻塞获取链接时间
     */
    private static final int DELAY = 1000;

    public AcceptThread(Server server, ClientService clientService) {
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
                Socket connection = this.server.connection();
                logger.info("Get connection from : {}", connection.getInetAddress().toString());
                clientService.addClient(new ClientObject(connection));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
