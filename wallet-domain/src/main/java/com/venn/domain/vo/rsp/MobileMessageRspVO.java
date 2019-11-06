package com.venn.domain.vo.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/31 14:14
 */
@Data
public class MobileMessageRspVO implements Serializable {

    private static final long serialVersionUID = -1885607681095682776L;

    private Long id;

    private String eventName;

    private String messageText;

    private String recipient;

    private Long countRecipient;

    private String failRecipient;

    private Long countFail;

    private String storeCode;

    private Byte status;

    private String author;

    private String reviewer;

    private String reasonRejection;

    private Date pushMessageTime;

    private Date reviewTime;
}
