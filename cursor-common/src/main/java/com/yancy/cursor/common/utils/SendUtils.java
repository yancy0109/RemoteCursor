package com.yancy.cursor.common.utils;

import com.yancy.cursor.common.SendType;

/**
 *
 * @author yancy0109
 */
public class SendUtils {

    public static byte[] castMovedBytes(int x, int y) {
        byte[] res = new byte[] {
            SendType.TYPE_MOUSE_MOVE,
            (byte) (x >> 8),
            (byte) (x),
            (byte) (y >> 8),
            (byte) (y)
        };
        return res;
    }
    public static byte[] castWheelMovedBytes(int x, int y) {
        byte[] res = new byte[] {
            SendType.TYPE_MOUSE_WHEEl,
            0,
            (byte) (x),
            0,
            (byte) (y)
        };
        return res;
    }

}
