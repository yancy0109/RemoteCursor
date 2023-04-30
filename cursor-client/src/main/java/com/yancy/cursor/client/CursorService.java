package com.yancy.cursor.client;

/**
 * @author yancy0109
 */
public interface CursorService {

    Command getCommand(byte[] bytes);

    void workAccordingToCommand(Command command);

}
