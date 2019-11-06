package com.venn.utils;

import com.venn.domain.IResponse;
import com.venn.domain.enums.ErrorCodeMsgEnum;


/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/6 11:08
 */
public class IResponseUtil {

    public static <T> IResponse<T> success(T data) {
        IResponse<T> response = new IResponse<>();
        response.setCode(200L);
        response.setMsg("请求成功");
        response.setData(data);
        response.setSuccess(true);
        return response;
    }

    public static <T> IResponse<T> fail(ErrorCodeMsgEnum codeMsg) {
        IResponse<T> response = new IResponse<>();
        response.setMsg(codeMsg.getMsg());
        response.setCode(codeMsg.getCode());
        response.setSuccess(false);
        return response;
    }

}
