package com.yancy.cursor.server;

import com.yancy.cursor.server.service.RemoteCursorServer;
import com.yancy.cursor.server.service.impl.BroadcastServiceImpl;
import com.yancy.cursor.server.service.impl.RemoteCursorServerImpl;

/**
 *
 * @author yancy0109
 */
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelListener;

import java.awt.*;

public class App implements NativeMouseInputListener, NativeMouseWheelListener {

    private BroadcastServiceImpl broadcastService;

    public App(String addr, int port) {
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
//        this.broadcastService.sendMouseMoved((int) MouseInfo.getPointerInfo().getLocation().getX(), (int) MouseInfo.getPointerInfo().getLocation().getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeEvent) {
        this.broadcastService.sendMousewheelMoved(nativeEvent.getWheelDirection(), nativeEvent.getWheelRotation());
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

        // Construct the example object.
        App app = new App("127.0.0.1", 11451);
        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(app);
        GlobalScreen.addNativeMouseMotionListener(app);
        GlobalScreen.addNativeMouseWheelListener(app);
    }

}