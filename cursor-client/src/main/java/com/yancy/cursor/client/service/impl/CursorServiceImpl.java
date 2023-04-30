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
        return new Command(
                bytes[0],
                bytes[1],
                bytes[2]
        );
    }

    @Override
    public void workAccordingToCommand(Command command) {
        byte commandType = command.getCommandType();
        switch (commandType) {
            case 1 : logger.info("Mouse Moved position : {}, {}", command.getArg1(), command.getArg2()); break;
            case 2 : robot.mouseWheel(command.getArg1() * command.getArg2()); break;
        }
    }

}
