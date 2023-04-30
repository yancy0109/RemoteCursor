package com.yancy.cursor.client.service;

/**
 * @author yancy0109
 */
public interface CursorService {

    Command getCommand(byte[] bytes);

    void workAccordingToCommand(Command command);

}
