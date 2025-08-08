package com.timezero.seckill.aspects;

import com.timezero.seckill.dto.HttpResult;
import com.timezero.seckill.enums.ResponseStsEnum;
import com.timezero.seckill.exceptions.TZBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author TimeZero
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleException(Throwable e) {
        return HttpResult.failed(ResponseStsEnum.BUSINESS_BUSY.getStsCode(), ResponseStsEnum.BUSINESS_BUSY.getStsMsg());
    }

    @ExceptionHandler(TZBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleException(TZBaseException e) {
        return HttpResult.failed(e.getStsCode(), e.getStsMsg());
    }

}
