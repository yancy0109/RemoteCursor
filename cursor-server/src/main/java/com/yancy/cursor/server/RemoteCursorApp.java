package com.yancy.cursor.server;

import com.yancy.cursor.server.service.impl.BroadcastServiceImpl;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 *
 * @author yancy0109
 */
public class RemoteCursorApp implements NativeMouseInputListener, NativeMouseWheelListener{

    private static final Logger logger = LoggerFactory.getLogger(NativeMouseInputListener.class);

    private BroadcastServiceImpl broadcastService;

    public RemoteCursorApp(String addr, int port) {
        this.broadcastService = new BroadcastServiceImpl(addr, port);
        broadcastService.serviceInit();
    }

    public void nativeMouseClicked(NativeMouseEvent e) {
    }

    public void nativeMousePressed(NativeMouseEvent e) {
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        //logger.info("Mouse Moved , {},{}", x, y);
        this.broadcastService.sendMouseMoved(x, y);
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeEvent) {
        int wheelDirection = nativeEvent.getWheelDirection();
        int wheelRotation = nativeEvent.getWheelRotation();
        //logger.info("Mouse WheelMoved , {},{}", wheelDirection, wheelRotation);
        this.broadcastService.sendMousewheelMoved(wheelDirection, wheelRotation);
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        RemoteCursorApp remoteCursorApp = new RemoteCursorApp("192.168.3.54", 11451);
        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(remoteCursorApp);
        GlobalScreen.addNativeMouseMotionListener(remoteCursorApp);
        GlobalScreen.addNativeMouseWheelListener(remoteCursorApp);
    }

}