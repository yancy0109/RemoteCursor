package com.yancy.cursor.server.client.service.impl;

import com.yancy.cursor.server.client.object.ClientObject;
import com.yancy.cursor.server.client.object.SelfWindowTarget;
import com.yancy.cursor.server.client.object.SelfWindowTargetImplClient;
import com.yancy.cursor.server.client.object.WindowClient;
import com.yancy.cursor.server.client.service.BarrierService;
import com.yancy.cursor.server.client.service.SelectService;
import com.yancy.cursor.server.service.server.ClientAddService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 选择器服务实现类
 * 实现可以获取当前控制类实体对象
 * 提供接口添加新的客户端对象
 * @author yancy0109
 */
public class SelectServiceImpl implements SelectService, ClientAddService {

    private WindowClient point = new SelfWindowTargetImplClient(null);

    /**
     * 提供屏幕遮障服务
     */
    private BarrierService barrierService = new DefaultBarrierService();

    private static Logger logger = LoggerFactory.getLogger(SelectService.class);

    @Override
    public ClientObject getCLientObject() {
        if (point instanceof SelfWindowTarget) {
            barrierService.close();
            return null;
        }
        barrierService.open();
        return point.clientObject;
    }

    @Override
    public void addClient(ClientObject clientObject) {
        logger.info("添加右屏幕");
        point.setRight(
                new WindowClient(clientObject)
                        .setLeft(point)
                        .getSelf()
        );
    }


    @Override
    public void move(int x, int y) {
        if (x == 0) {
            selectLeft();
            return;
        }
        if (x == 1919) {
            selectRight();
            return;
        }
        if (y == 0) {
            selectTop();
            return;
        }
        if (y == 1079) {
            selectDown();
        }
    }

    @Override
    public synchronized void selectLeft() {
        if (point.getLeft() == null) {
            return;
        }
        logger.info("鼠标移至左屏幕");
        point = point.getLeft();
    }

    @Override
    public synchronized void selectRight() {
        if (point.getRight() == null) {
            return;
        }

        logger.info("鼠标移至右屏幕");
        point = point.getRight();
    }

    @Override
    public synchronized void selectTop() {
        if (point.getTop() == null) {
            return;
        }
        logger.info("鼠标移至上屏幕");
        point = point.getTop();
    }

    @Override
    public synchronized void selectDown() {
        if (point.getDown() == null) {
            return;
        }
        logger.info("鼠标移至下屏幕");
        point = point.getDown();
    }

    private boolean isPointSelfWindow() {
        if (this.point instanceof SelfWindowTarget) {
            return true;
        }
        return false;
    }

    @Override
    public void errorRemove() {
        if (this.point.getLeft() != null) {
            this.point = this.point.getLeft();
        }
        if (!(point instanceof SelfWindowTarget)) {
          throw new RuntimeException("Get Default Selector Point has error");
        }
    }
}
