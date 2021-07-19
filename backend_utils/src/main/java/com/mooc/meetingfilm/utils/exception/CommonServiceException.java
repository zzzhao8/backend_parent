package com.mooc.meetingfilm.utils.exception;

import lombok.Data;

/**
 * @ClassName: CommonServiceException
 * @description: 公共的业务逻辑错误
 * @date: 2021/7/18 19:34
 */
@Data
public class CommonServiceException extends Exception {

    private Integer code;
    private String message;

    public CommonServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
