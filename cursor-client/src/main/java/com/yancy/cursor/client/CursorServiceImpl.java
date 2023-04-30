package com.yancy.cursor.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yancy0109
 */
public class CursorServiceImpl extends ClientImpl implements CursorService {

    private static final Logger logger = LoggerFactory.getLogger(CursorService.class);

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
            case 2 : logger.info("Mouse Wheel Moved, direction:{}, Rotation:{}", command.getArg1(), command.getArg2()); break;
        }
    }

}
