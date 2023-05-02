package com.yancy.cursor.client.service;

/**
 * @author yancy0109
 */
public class Command {

    private byte commandType;

    private byte arg1;

    private byte arg2;

    private byte arg3;

    private byte arg4;

    private byte arg5;

    private byte arg6;

    public Command(byte commandType, byte arg1, byte arg2, byte arg3, byte arg4, byte arg5, byte arg6) {
        this.commandType = commandType;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
        this.arg5 = arg5;
        this.arg6 = arg6;
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

    public byte getArg3() {
        return arg3;
    }

    public void setArg3(byte arg3) {
        this.arg3 = arg3;
    }

    public byte getArg4() {
        return arg4;
    }

    public void setArg4(byte arg4) {
        this.arg4 = arg4;
    }

    public byte getArg5() {
        return arg5;
    }

    public void setArg5(byte arg5) {
        this.arg5 = arg5;
    }

    public byte getArg6() {
        return arg6;
    }

    public void setArg6(byte arg6) {
        this.arg6 = arg6;
    }
}
