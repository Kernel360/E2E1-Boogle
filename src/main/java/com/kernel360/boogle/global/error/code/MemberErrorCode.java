package com.kernel360.boogle.global.error.code;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    NOT_FOUND_MEMBER(404, "회원을 찾을 수 없습니다."),
    ALREADY_SIGNED_UP_MEMBER(409, "이미 등록된 유저입니다.");

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
