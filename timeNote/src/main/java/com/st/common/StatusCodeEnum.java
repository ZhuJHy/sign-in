package com.st.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: StatusCodeEnum
 * @Version: 1.0
 * @Description: TODO
 * @Author song
 * @Date 2022/8/11 20:16
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    /**
     * 操作失败
     */
    FAIL(200, "操作失败"),

    /**
     * 操作成功
     */
    SUCCESS(201, "操作成功"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR(505, "系统异常");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
}
