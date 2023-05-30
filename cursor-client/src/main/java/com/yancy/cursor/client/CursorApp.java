package com.yancy.cursor.client;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.yancy.cursor.client.service.Command;
import com.yancy.cursor.client.service.impl.CursorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author yancy0109
 */
public class CursorApp extends CursorServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(CursorApp.class);

    /**
     * 循环读入 Byte，解析为 Command，进行解析调用鼠标
     * @param addr
     * @param port
     */
    void run(String addr, int port) {
        this.connServer(addr, port);
        while (true) {
            byte[] bytes = read();
            Command command = getCommand(bytes);
            workAccordingToCommand(command);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        logger.info("Please Input address of Cursor Server");
        final String addr = scanner.nextLine();
        logger.info("Please Input port of Cursor Server");
        final int port = scanner.nextInt();
        new CursorApp().run(addr, port);
        try {
            // 初始化 GlobalScreen
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("无法初始化 JNativeHook");
            System.exit(1);
        }
    }
}
