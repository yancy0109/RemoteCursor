package com.yancy.cursor.server.service;

/**
 * @author yancy0109
 */
public interface CursorService {

    /**
     * 同步当前鼠标状态
     */
    void updatePosition(int x, int y);

}
