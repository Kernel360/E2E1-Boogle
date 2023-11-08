package com.kernel360.boogle.member.exception;

/**
 * 이미 등록된 유저를 재등록하려고 할때 발생하는 Exception
 */
public class AlreadySignedupMemberException extends RuntimeException {

    public AlreadySignedupMemberException(String message) {
        super(message);
    }

    public AlreadySignedupMemberException() {
        super("이미 등록된 유저입니다.");
    }
}
