package com.venn.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dwf
 * @date 2019/9/27 22:21
 */

@Data
public class ExpectedEntity implements Serializable {

    private static final long serialVersionUID = -4497711506510092416L;

    private String name;

    private Integer age;

    private Double height;
}
