package com.yancy.cursor.server.service.impl;

import com.yancy.cursor.common.utils.SendUtils;
import com.yancy.cursor.server.client.ClientObject;
import com.yancy.cursor.server.service.BroadcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author yancy0109
 */
public class BroadcastServiceImpl extends RemoteCursorServerImpl implements BroadcastService {

    private static final Logger logger = LoggerFactory.getLogger(BroadcastService.class);

    public BroadcastServiceImpl(String serverAddr, int serverPort) {
        super(serverAddr, serverPort);
    }

    @Override
    public void serviceInit() {
        super.run();
    }

    @Override
    public void sendMouseMoved(int x, int y) {
        ByteBuffer src = ByteBuffer.wrap(SendUtils.castMovedBytes(x, y));
        for (ClientObject clientObject : this.clientList) {
            try {
                clientObject.getSocketChannel().write(src);
            } catch (IOException e) {
                logger.warn("There has a error when send Mouse Moved Msg. Caused by IOException");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void sendMousewheelMoved(int wheelDirection, int wheelRotation) {
        ByteBuffer src = ByteBuffer.wrap(SendUtils.castWheelMovedBytes(wheelDirection, wheelRotation));
        logger.info("The nums of cilent is {}.", clientList.size());
        for (ClientObject clientObject : this.clientList) {
            System.out.println(clientObject);
            try {
                clientObject.getSocketChannel().write(src);
            } catch (IOException e) {
                logger.warn("There has a error when send Mouse Wheel Moved Msg. Caused by IOException");
                throw new RuntimeException(e);
            }
        }
    }
}