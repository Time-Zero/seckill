package com.timezero.seckill.exceptions;

import com.timezero.seckill.enums.ResponseStsEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义项目基异常
 *
 * @author TimeZero
 */
@Getter
@Setter
public class TZBaseException extends RuntimeException {
    private final String stsCode;
    private final String stsMsg;

    public TZBaseException(ResponseStsEnum responseStsEnum) {
        super(responseStsEnum.getStsMsg());
        this.stsCode = responseStsEnum.getStsCode();
        this.stsMsg = responseStsEnum.getStsMsg();
    }
}
