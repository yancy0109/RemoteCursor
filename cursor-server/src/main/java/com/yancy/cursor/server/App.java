package com.yancy.cursor.server;

import com.yancy.cursor.server.service.RemoteCursorServer;
import com.yancy.cursor.server.service.impl.RemoteCursorServerImpl;

/**
 *
 * @author yancy0109
 */
public class App {
    public static void main(String[] args) {
        RemoteCursorServer remoteCursorServer = new RemoteCursorServerImpl("127.0.0.1", 10002);
        remoteCursorServer.run();

        while (true);
    }
}
