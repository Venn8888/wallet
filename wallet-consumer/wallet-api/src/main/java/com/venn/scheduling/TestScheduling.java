package com.venn.scheduling;

import com.venn.domain.RedisLock;
import com.venn.manager.RedisLockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author venn
 * @version 1.0.0
 * @date 2019/10/21 10:47
 */
@Component
public class TestScheduling {

    private static final Logger log = LoggerFactory.getLogger(TestScheduling.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final RedisLock REDIS_LOCK = new RedisLock("testSchedulingKey", "testSchedulingValue");

    @Value("${venn:李白}")
    private String name;

    private final RedisLockManager redisLockManager;

    public TestScheduling(RedisLockManager redisLockManager) {
        this.redisLockManager = redisLockManager;
    }

    @Scheduled(cron = "0/5 * *  * * ?")
    public void testScheduling() throws Exception {

        if (redisLockManager.tryLock(REDIS_LOCK)) {
            log.info(name + "1得到了锁");
            log.info(name + "1定时任务触发了，当前时间是：" + FORMAT.format(new Date()));
            Thread.sleep(6000);
            redisLockManager.releaseLock(REDIS_LOCK);
            log.info(name + "1锁被释放了");
        } else {
            log.info("1未获得锁，暂缓操作");
        }
    }

    @Scheduled(fixedRate = 3000)
    public void testScheduling2() throws Exception {
        if (redisLockManager.tryLock(REDIS_LOCK)) {
            log.info("2得到了锁");
            log.info("2定时任务触发了，当前时间是：" + FORMAT.format(new Date()));
            Thread.sleep(3000);
            redisLockManager.releaseLock(REDIS_LOCK);
            log.info("2锁被释放了");
        } else {
            log.info("2未获得锁，暂缓操作");
        }
    }

}
