package com.yancy.cursor.client.service.impl;

import com.yancy.cursor.client.service.Command;
import com.yancy.cursor.client.service.CursorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

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

    @Override
    public Command getCommand(byte[] bytes) {
        if (bytes.length != 5) {
            throw new RuntimeException("Received Info from Server has error.");
        }
        return new Command(
                bytes[0],
                bytes[1],
                bytes[2],
                bytes[3],
                bytes[4]
        );
    }

    @Override
    public void workAccordingToCommand(Command command) {
        byte commandType = command.getCommandType();
        int x = ((command.getArg1() & FF16) << 8) | (command.getArg2() & FF16);
        int y = ((command.getArg3() & FF16) << 8) | (command.getArg4() & FF16);
        switch (commandType) {
            case 1 :
                    logger.info("Mouse Moved position , {},{}", x, y);
                    robot.mouseMove(x, y);
                    break;
            case 2 :
                    logger.info("Mouse Moved WheelMoved , {},{}",  x, y != 1 ? -1 : 1);
                    robot.mouseWheel(x * y);
                    break;
        }
    }

}
