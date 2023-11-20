package com.kernel360.boogle.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReplyErrorCode implements ErrorCode {
    EMPTY_CONTENT_REPLY(401, "공백은 입력될 수 없습니다.");

    private final int status;
    private final String message;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
