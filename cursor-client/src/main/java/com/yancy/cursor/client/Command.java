package com.yancy.cursor.client;

/**
 * @author yancy0109
 */
public class Command {

    private byte commandType;

    private byte arg1;

    private byte arg2;

    public Command(byte commandType, byte arg1, byte arg2) {
        this.commandType = commandType;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public byte getCommandType() {
        return commandType;
    }

    public void setCommandType(byte commandType) {
        this.commandType = commandType;
    }

    public byte getArg1() {
        return arg1;
    }

    public void setArg1(byte arg1) {
        this.arg1 = arg1;
    }

    public byte getArg2() {
        return arg2;
    }

    public void setArg2(byte arg2) {
        this.arg2 = arg2;
    }
}
