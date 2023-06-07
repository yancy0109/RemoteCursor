package com.yancy.cursor.server.service.impl;

import com.yancy.cursor.common.utils.SendUtils;
import com.yancy.cursor.server.client.object.ClientObject;
import com.yancy.cursor.server.service.BroadcastService;
import com.yancy.cursor.server.service.server.impl.CursorServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * 发送器实现类
 * @author yancy0109
 */
public class BroadcastServiceImpl extends CursorServerImpl implements BroadcastService {

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
        clientObject.writeAction(src);
    }

    @Override
    public void sendMousewheelMoved(int wheelDirection, int wheelRotation) {
        ClientObject clientObject = selectService.getCLientObject();
        if (clientObject == null) {
            return;
        }
        ByteBuffer src = ByteBuffer.wrap(SendUtils.castWheelMovedBytes(wheelDirection, wheelRotation));
        clientObject.writeAction(src);
    }

    @Override
    public void sendMouseClicked(int x, int y, int buttonType, int clickTime) {
        ClientObject clientObject = selectService.getCLientObject();
        if (clientObject == null) {
            return;
        }
        ByteBuffer src = ByteBuffer.wrap(SendUtils.castMouseClicked(x, y, buttonType, clickTime));
        clientObject.writeAction(src);
    }
}
