package com.yancy.cursor.client;

/**
 * @author yancy0109
 */
public class CursorApp extends CursorServiceImpl{

    void run(String addr, int port) {
        this.connServer(addr, port);
        while (true) {
            byte[] bytes = read();
            Command command = getCommand(bytes);
            workAccordingToCommand(command);
        }
    }

    public static void main(String[] args) {
        new CursorApp().run("127.0.0.1", 11451);
    }
}
