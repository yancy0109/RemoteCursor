package com.yancy.cursor.server.client.service;

import com.yancy.cursor.server.client.ClientObject;

/**
 * ClientObject 选择器
 * @author yancy0109
 */
public interface SelectService {

    ClientObject getCLientObject();

    void addClient(ClientObject clientObject);

    void move(int x, int y);

    void selectLeft();
    void selectRight();
    void selectTop();
    void selectDown();
}
