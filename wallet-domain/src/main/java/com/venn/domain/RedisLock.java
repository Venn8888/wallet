package com.venn.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisLock implements Serializable {

    private static final long serialVersionUID = -3811667048438374355L;

    public RedisLock(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;

    private String value;
}
