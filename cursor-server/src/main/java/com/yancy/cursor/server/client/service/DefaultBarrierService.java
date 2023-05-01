package com.yancy.cursor.server.client.service;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * @author yancy0109
 */
public class DefaultBarrierService implements BarrierService {

    private static JFrame frame;
    static {
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.setOpacity(0.1f); // 设置透明度
        frame.getContentPane().setBackground(new Color(0, 0, 0, 0)); // 设置内容面板的透明颜色
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
    }

    @Override
    public void open() {
        frame.setVisible(true);
    }

    @Override
    public void close() {
        frame.setVisible(false);
    }

}
