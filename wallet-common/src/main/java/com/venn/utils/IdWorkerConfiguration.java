package com.venn.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/12/19
 */
@Configuration
public class IdWorkerConfiguration {
    Logger logger = LogManager.getLogger();

    @Value("${id.work:noWorkId}")
    private String workId;
    @Value("${id.dateSource:noDateSource}")
    private String dateSource;

    @Bean
    @Primary
    public IdGenerator idWorker() {
        return new IdGenerator(getWorkFromConfig(), getDateFromConfig());
    }

    private Long getWorkFromConfig() {
        if ("noWorkId".equals(workId)) {
            return getWorkId();
        } else {
            //将workId转换为Long
            return 2L;
        }
    }

    private Long getDateFromConfig() {
        if ("noDateSource".equals(dateSource)) {
            return getDataCenterId();
        } else {
            //将workId转换为Long
            return 2L;
        }
    }

    private Long getWorkId() {
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for (int b : ints) {
                sums += b;
            }
            return (long) (sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0, 31);
        }
    }

    private Long getDataCenterId() {
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i : ints) {
            sums += i;
        }
        return (long) (sums % 32);
    }

}
