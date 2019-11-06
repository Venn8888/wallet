package com.venn.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:20
 */
@Data
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 4679435616634161084L;

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
