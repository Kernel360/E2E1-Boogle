package com.kernel360.boogle.global.error.code;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ReplyErrorCode implements ErrorCode {
    EMPTY_CONTENT_REPLY(HttpStatus.BAD_REQUEST.value(), "공백은 입력될 수 없습니다."),
    VALIDATION_ERROR_CODE(HttpStatus.BAD_REQUEST.value(), "Validation Error");

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
