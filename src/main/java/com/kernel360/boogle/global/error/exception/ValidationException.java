package com.kernel360.boogle.global.error.exception;


import com.kernel360.boogle.global.error.code.ErrorCode;
import lombok.Getter;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

@Getter
public class ValidationException extends ConstraintViolationException {
    private final transient ErrorCode errorCode;

    public ValidationException(ErrorCode errorCode) {
        super(Collections.emptySet());
        this.errorCode = errorCode;
    }
}
