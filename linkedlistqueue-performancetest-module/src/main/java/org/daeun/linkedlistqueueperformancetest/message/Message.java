package org.daeun.linkedlistqueueperformancetest.message;

import lombok.ToString;

@ToString
public class Message {
    private String msg;

    public Message(String str) {
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }
}
