package com.venn.domain.vo.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:25
 */
@Data
public class UserInfoRspVO implements Serializable {

    private static final long serialVersionUID = -5593871597339129377L;

    private String userId;

    private String userName;

    private String realName;

    private Boolean sex;

    private Integer age;

    private String mobileNo;

    private String idNo;

    private String address;

    private String job;

    private Date birthday;

    private Byte status;
}
