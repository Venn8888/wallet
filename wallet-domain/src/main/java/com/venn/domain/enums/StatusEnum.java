package com.venn.domain.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 状态枚举类
 *
 * @author Venn
 */
@Getter
public enum StatusEnum {

    /**
     * 成功
     */
    SUC("200", "成功"),

    /**
     * 失败
     */
    FAIL("400", "失败");

    StatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;

    private static final List<String> STATUS_ENUM_CODE_LIST;

    static {
        StatusEnum[] values = StatusEnum.values();
        List<String> codeList = new ArrayList<>();
        for (StatusEnum value : values) {
            codeList.add(value.getCode());
        }
        STATUS_ENUM_CODE_LIST = Collections.unmodifiableList(codeList);
    }

    public static StatusEnum ofCode(String code) {
        return Arrays.stream(values()).filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }

    public static StatusEnum ofMsg(String msg) {
        return Arrays.stream(values()).filter(e -> e.msg.equals(msg)).findFirst().orElse(null);
    }

    public static List<String> getCodeList() {
        return STATUS_ENUM_CODE_LIST;
    }

    public static void main(String[] args) {
        List<String> codeList = StatusEnum.getCodeList();
        System.out.println(codeList);

        StatusEnum statusEnum = StatusEnum.ofCode("200");
        System.out.println(statusEnum);

        StatusEnum ofMsg = StatusEnum.ofMsg("失败");
        System.out.println(ofMsg);

        System.out.println(StatusEnum.ofCode("4163"));
    }
}
