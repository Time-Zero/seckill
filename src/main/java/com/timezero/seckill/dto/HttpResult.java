package com.timezero.seckill.dto;

import com.timezero.seckill.enums.ResponseStsEnum;
import lombok.Data;

/**
 * http 请求返回的最外层对象
 *
 * @param <T> 返回的数据
 * @author TimeZero
 */
@Data
public class HttpResult<T> {
    private String stsCode;
    private String stsMsg;
    private T data;

    /**
     * 响应成功
     *
     * @param data 响应的数据
     * @param <T>  响应的数据类型
     * @return 响应结果
     */
    public static <T> HttpResult<T> success(final T data) {
        HttpResult<T> result = new HttpResult<>();
        result.setStsCode(ResponseStsEnum.REQUEST_SUCCESS.getStsCode());
        result.setStsMsg(ResponseStsEnum.REQUEST_SUCCESS.getStsMsg());
        result.setData(data);
        return result;
    }


    /**
     * 响应失败
     *
     * @param stsCode 错误码
     * @param stsMsg  错误信息
     * @param <T>     响应的数据类型
     * @return 响应结果
     */
    public static <T> HttpResult<T> failed(final String stsCode, final String stsMsg) {
        HttpResult<T> result = new HttpResult<>();
        result.setStsCode(stsCode);
        result.setStsMsg(stsMsg);
        return result;
    }
}
