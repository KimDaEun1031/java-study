package org.daeun.linkedlistqueue.pcpattern;

import lombok.ToString;

//프로듀서가 메세지를 생성하거나 콘슈머가 메세지를 출력할 수 있게 해주는 클래스
@ToString
public class Message {
    private final String msg;

    public Message(String str) {
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }
}
