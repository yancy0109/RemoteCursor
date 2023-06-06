package com.yancy.cursor.server.service;

/**
 * 广播功能接口, 传递坐标动作信息
 * 交由 Cursor Listener 触发
 * @author yancy0109
 */
public interface BroadcastService {

    void serviceInit();

    void sendMouseMoved(int x, int y);

    void sendMousewheelMoved(int wheelDirection, int wheelRotation);

    void sendMouseClicked(int x, int y, int buttonType, int clickTime);
}
