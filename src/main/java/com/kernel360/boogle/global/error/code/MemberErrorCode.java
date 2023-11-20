package com.kernel360.boogle.global.error.code;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND.value(), "회원을 찾을 수 없습니다."),
    ALREADY_SIGNED_UP_MEMBER(HttpStatus.CONFLICT.value(), "이미 등록된 유저입니다.");

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
