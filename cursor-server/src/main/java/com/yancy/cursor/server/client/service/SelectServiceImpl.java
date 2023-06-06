package com.yancy.cursor.server.client.service;

import com.yancy.cursor.server.client.ClientObject;
import com.yancy.cursor.server.client.SelfWindowTarget;
import com.yancy.cursor.server.client.SelfWindowTargetImplClient;
import com.yancy.cursor.server.client.WindowClient;
import com.yancy.cursor.server.service.ClientAddService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yancy0109
 */
public class SelectServiceImpl implements SelectService, ClientAddService {

    private WindowClient point = new SelfWindowTargetImplClient(null);

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
