package com.yancy.cursor.server.client;

import java.nio.channels.SocketChannel;

/**
 * 为客户端加上方向属性
 * @author yancy0109
 */
public class WindowClient {

    public ClientObject clientObject;

    public WindowClient(ClientObject clientObject) {
        this.clientObject = clientObject;
        self = this;
    }

    private WindowClient self;
    private WindowClient top;
    private WindowClient down;
    private WindowClient right;
    private WindowClient left;

    public WindowClient getSelf() {
        return self;
    }


    public WindowClient getTop() {
        return top;
    }

    public WindowClient setTop(WindowClient top) {
        this.top = top;
        return this;
    }

    public WindowClient getDown() {
        return down;
    }

    public WindowClient setDown(WindowClient down) {
        this.down = down;
        return this;
    }

    public WindowClient getRight() {
        return right;
    }

    public WindowClient setRight(WindowClient right) {
        this.right = right;
        return this;
    }

    public WindowClient getLeft() {
        return left;
    }

    public WindowClient setLeft(WindowClient left) {
        this.left = left;
        return this;
    }
}
