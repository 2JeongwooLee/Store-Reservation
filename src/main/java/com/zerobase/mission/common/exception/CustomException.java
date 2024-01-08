package com.zerobase.mission.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMessage;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
