package com.venn.domain.enums;

import lombok.Getter;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/6 10:45
 */
@Getter
public enum ErrorCodeMsgEnum {

    FAIL(400L, "系统错误"),

    DATA_NOT_EXIST(201L, "数据不存在"),

    AOP_RSA_DECRYPT_FAIL(202L, "解密切面rsa解密错误"),

    ;

    private Long code;

    private String msg;

    ErrorCodeMsgEnum(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
