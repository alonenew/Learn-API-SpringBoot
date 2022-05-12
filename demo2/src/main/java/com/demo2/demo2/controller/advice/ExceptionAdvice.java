package com.demo2.demo2.controller.advice;

import com.demo2.demo2.constant.ResultCode;
import com.demo2.demo2.exception.CommonException;
import com.demo2.demo2.model.common.ErrorResponse;

import org.apache.commons.logging.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    private Log log = LogFactory.getLog(ExceptionAdvice.class);

    private ResponseEntity<Object> handleErrorResponse(String errorCode, String errorMessage, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(errorCode);
        errorResponse.setDesc(errorMessage);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(CommonException.class)

    public ResponseEntity<Object> handleCommonException(CommonException ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return handleErrorResponse(ex.getCode(), ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return handleErrorResponse(ResultCode.ERR_CODE_500, ResultCode.ERR_DESC_500, HttpStatus.OK);
    }
}
