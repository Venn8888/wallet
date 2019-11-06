package com.venn.domain.vo.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:24
 */
@Data
public class UserInfoReqVO implements Serializable {

    private static final long serialVersionUID = -8191371875726753855L;

    private String userId;

    private String mobileNo;

    private String idNo;
}
