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

    /**
     * 移除当前指向 ClientObject 抛弃连接
     * 切换指向指针为 当前主机 SelfWindowTargetImplClient 对象
     */
    void errorRemove();
}
