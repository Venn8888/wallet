package com.venn.exception;

import com.venn.domain.enums.ErrorCodeMsgEnum;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/6 14:54
 */
@Getter
@ToString
public class WalletException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 2831064924674146572L;

    private Long code;

    private String msg;

    public WalletException() {
    }

    public WalletException(String message) {
        super(message);
    }

    public WalletException(Long code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public WalletException(ErrorCodeMsgEnum codeMsgEnum) {
        super(codeMsgEnum.toString());
        this.code = codeMsgEnum.getCode();
        this.msg = codeMsgEnum.getMsg();
    }
}
