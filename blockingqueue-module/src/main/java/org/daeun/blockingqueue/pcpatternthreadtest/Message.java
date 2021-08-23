package org.daeun.blockingqueue.pcpatternthreadtest;

public class Message {
    private final String msg;

    public Message(String str) {
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }
}
