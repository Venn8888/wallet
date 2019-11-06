package com.venn.domain.enums;

import lombok.Getter;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/6 10:45
 */
@Getter
public enum ErrorCodeMsgEnum {

   FAIL(400L,"系统错误"),

   DATA_NOT_EXIST(201L,"数据不不存在");

   private Long code;

   private String msg;

   ErrorCodeMsgEnum(Long code,String msg){
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
