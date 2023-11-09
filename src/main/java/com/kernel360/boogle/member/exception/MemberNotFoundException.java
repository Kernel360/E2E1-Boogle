package com.kernel360.boogle.member.exception;

/**
 * 유저를 찾을 수 없을 때 발생하는 Exception
 */
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException() {
        super("회원을 찾을 수 없습니다.");
    }
}
