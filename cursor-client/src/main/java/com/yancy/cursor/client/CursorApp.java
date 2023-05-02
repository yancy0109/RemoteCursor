package com.yancy.cursor.client;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.yancy.cursor.client.service.Command;
import com.yancy.cursor.client.service.impl.CursorServiceImpl;

/**
 * @author yancy0109
 */
public class CursorApp extends CursorServiceImpl implements NativeMouseInputListener {

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeEvent) {
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeEvent) {
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeEvent) {
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeEvent) {
    }

    void run(String addr, int port) {
        this.connServer(addr, port);
        while (true) {
            byte[] bytes = read();
            Command command = getCommand(bytes);
            workAccordingToCommand(command);
        }
    }

    public static void main(String[] args) {
        String addr = "192.168.3.54";
        if (args.length == 2 && args[1] != null) {
            addr = args[1];
        }
        new CursorApp().run(addr, 11451);
        try {
            // 初始化 GlobalScreen
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("无法初始化 JNativeHook");
            System.exit(1);
        }

        // 添加 NativeMouseInputListener
        GlobalScreen.addNativeMouseListener(new CursorApp());
    }
}
