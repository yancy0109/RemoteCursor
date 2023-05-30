package com.yancy.cursor.client.service.impl;

import com.yancy.cursor.client.service.Command;
import com.yancy.cursor.client.service.CursorService;
import com.yancy.cursor.common.SendLength;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * @author yancy0109
 */
public class CursorServiceImpl extends ClientImpl implements CursorService {

    private static final int FF16 = 0xff;

    private static final Logger logger = LoggerFactory.getLogger(CursorService.class);

    private Robot robot;    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     *
     * @param bytes 获取服务器传输 Bytes
     */
    public void resolveBytes(byte[] bytes) {
        // 解析为 Command
        Command command = getCommand(bytes);
        // 调用 Robot
        workAccordingToCommand(command);
    }

    @Override
    public Command getCommand(byte[] bytes) {
        if (bytes.length != SendLength.HEAD_LENGTH) {
            throw new RuntimeException("Received Info from Server has error.");
        }
        return new Command(
                bytes[0],
                bytes[1],
                bytes[2],
                bytes[3],
                bytes[4],
                bytes[5],
                bytes[6]
        );
    }

    @Override
    public void workAccordingToCommand(Command command) {
        byte commandType = command.getCommandType();
        int x = ((command.getArg1() & FF16) << 8) | (command.getArg2() & FF16);
        int y = ((command.getArg3() & FF16) << 8) | (command.getArg4() & FF16);
        switch (commandType) {
            case 1 :
                    robot.mouseMove(x, y);
                    break;
            case 2 :
                    robot.mouseWheel(x * y);
                    break;
            case 3:
                // 将鼠标移动到需要单击的位置
                robot.mouseMove(x, y);
                // 按下并释放鼠标左键（模拟单击）
                for (int i = 0; i < command.getArg6(); i++) {
                    robot.mousePress(InputEvent.getMaskForButton(command.getArg5()));
                    robot.mouseRelease(InputEvent.getMaskForButton(command.getArg5()));
                }
                break;
        }
    }

}
