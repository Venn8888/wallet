package com.venn.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/6 10:42
 */
@Data
public class IResponse<T> implements Serializable {

    private static final long serialVersionUID = 3837433022900899604L;

    private Long code;

    private String msg;

    private boolean success;

    private T data;

}
