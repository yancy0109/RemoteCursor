package com.yancy.cursor.server;

import com.yancy.cursor.server.service.BroadcastService;
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
import java.util.Scanner;

/**
 *
 * @author yancy0109
 */
public class RemoteCursorApp implements NativeMouseInputListener, NativeMouseWheelListener{

    private static final Logger logger = LoggerFactory.getLogger(NativeMouseInputListener.class);

    private BroadcastService broadcastService;

    public RemoteCursorApp(String addr, int port) {
        this.broadcastService = new BroadcastServiceImpl(addr, port);
        // init Service
        broadcastService.serviceInit();
    }

    public void nativeMouseClicked(NativeMouseEvent e) {
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        int button = e.getButton(); // 获取鼠标按钮（左、中、右）
        int clickCount = e.getClickCount(); // 点击次数
        this.broadcastService.sendMouseClicked(x, y, button, clickCount);
    }

    public void nativeMousePressed(NativeMouseEvent e) {
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        this.broadcastService.sendMouseMoved(x, y);
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeEvent) {
        int wheelDirection = nativeEvent.getWheelDirection();
        int wheelRotation = nativeEvent.getWheelRotation();
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
        Scanner scanner = new Scanner(System.in);
        logger.info("Please Input System Address");
        final String addr = scanner.next();
        logger.info("Please Input System Port");
        final int port = scanner.nextInt();
        // new App
        RemoteCursorApp remoteCursorApp = new RemoteCursorApp(addr, port);
        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(remoteCursorApp);
        GlobalScreen.addNativeMouseMotionListener(remoteCursorApp);
        GlobalScreen.addNativeMouseWheelListener(remoteCursorApp);
    }

}