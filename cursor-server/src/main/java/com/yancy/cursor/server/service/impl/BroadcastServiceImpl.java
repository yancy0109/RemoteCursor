package com.yancy.cursor.server.service.impl;

import com.yancy.cursor.common.utils.SendUtils;
import com.yancy.cursor.server.client.ClientObject;
import com.yancy.cursor.server.service.BroadcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 发送器实现类
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
        selectService.move(x, y);
        ClientObject clientObject = this.selectService.getCLientObject();
        if (clientObject == null) {
            return;
        }
        ByteBuffer src = ByteBuffer.wrap(SendUtils.castMovedBytes(x, y));
        try {
            clientObject.getSocketChannel().write(src);
        } catch (IOException e) {
            selectService.errorRemove();
            logger.warn("There has a error when send Mouse Moved Msg. Caused by IOException");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMousewheelMoved(int wheelDirection, int wheelRotation) {
        ClientObject cLientObject = selectService.getCLientObject();
        if (cLientObject == null) {
            return;
        }
        ByteBuffer src = ByteBuffer.wrap(SendUtils.castWheelMovedBytes(wheelDirection, wheelRotation));
        try {
            cLientObject.getSocketChannel().write(src);
        } catch (IOException e) {
            selectService.errorRemove();
            logger.warn("There has a error when send Mouse Wheel Moved Msg. Caused by IOException");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMouseClicked(int x, int y, int buttonType, int clickTime) {
        ClientObject cLientObject = selectService.getCLientObject();
        if (cLientObject == null) {
            return;
        }
        ByteBuffer src = ByteBuffer.wrap(SendUtils.castMouseClicked(x, y, buttonType, clickTime));
        try {
            cLientObject.getSocketChannel().write(src);
        } catch (IOException e) {
            selectService.errorRemove();
            logger.warn("There has a error when send Mouse Wheel Moved Msg. Caused by IOException");
            throw new RuntimeException(e);
        }
    }
}
