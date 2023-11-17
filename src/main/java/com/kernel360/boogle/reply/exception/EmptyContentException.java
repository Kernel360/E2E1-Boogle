package com.kernel360.boogle.reply.exception;

public class EmptyContentException extends IllegalArgumentException {

    public EmptyContentException() {
        super("공백은 입력될 수 없습니다.");
    }

    public EmptyContentException(String message) {
        super(message);
    }
}
