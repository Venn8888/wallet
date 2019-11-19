package com.venn.domain.enums;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间范围枚举
 *
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/14 9:18
 */
@Getter
public enum TimeLimitEnum {

    NEARLY_SEVEN_DAYS(1, "近7天", getPastDate(7, 1)),
    NEARLY_THIRTY_DAYS(2, "近30天", getPastDate(30, 1)),
    NEARLY_NINETY_DAYS(3, "近90天", getPastDate(90, 1)),
    NEARLY_HALF_YEAR(4, "近半年", getPastDate(180, 1)),
    NEARLY_ONE_YEAR(5, "近一年", getPastDate(365, 1)),
    ;

    private int code;
    private String desc;
    private String timeLimit;

    TimeLimitEnum(int code, String desc, String timeLimit) {
        this.code = code;
        this.desc = desc;
        this.timeLimit = timeLimit;
    }

    public static TimeLimitEnum getFromCode(Integer code) {
        return Arrays.stream(values()).filter(e -> e.code == code).findFirst().orElse(null);
    }

    public static TimeLimitEnum getFromDesc(String desc) {
        return Arrays.stream(values()).filter(e -> e.desc.equals(desc)).findFirst().orElse(null);
    }


    /**
     * 获取过去第几天到第几天的日期
     *
     * @param past1  天数
     * @param past2  天数
     * @return String
     */
    public static String getPastDate(int past1, int past2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_YEAR, calendar1.get(Calendar.DAY_OF_YEAR) - past1);
        Date frontPast = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_YEAR, calendar2.get(Calendar.DAY_OF_YEAR) - past2);
        Date frontOne = calendar2.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(frontPast) + " ~ " + dateFormat.format(frontOne);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append(i).append(",");
        }
        String s = sb.toString();

        String[] split = s.split(",");
        System.out.println(split.length);


    }
}