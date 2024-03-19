package com.st.execption;


import com.st.common.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.st.common.StatusCodeEnum.FAIL;


/**
 * @ClassName: ServiceException
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/8/11 20:15
 */
@Getter
@AllArgsConstructor
public class ServiceException extends RuntimeException{

    /**
     * 错误码
     */
    private Integer code = FAIL.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public ServiceException(String message) {
        this.message = message;
    }


    public ServiceException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
