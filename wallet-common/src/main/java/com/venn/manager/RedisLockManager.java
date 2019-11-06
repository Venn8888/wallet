package com.venn.manager;

import com.venn.domain.RedisLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/4 15:23
 */
@Component
public class RedisLockManager {

    private static final Logger log = LogManager.getLogger(RedisLockManager.class);

    /**
     * 单个业务持有锁的时间10s，防止死锁
     */
    private final static long LOCK_EXPIRE = 10 * 1000L;
    /**
     * 默认30ms尝试一次
     */
    private final static long LOCK_TRY_INTERVAL = 30L;
    /**
     * 默认尝试20s
     */
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;

    @Autowired
    private StringRedisTemplate template;

    /**
     * 尝试获取全局锁
     *
     * @param lock 锁的名称
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(RedisLock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock    锁的名称
     * @param timeout 获取超时时间 单位ms
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(RedisLock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock        锁的名称
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(RedisLock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取锁的超时时间
     * @param tryInterval    多少毫秒尝试获取一次
     * @param lockExpireTime 锁的过期
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(RedisLock lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 操作redis获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取的超时时间
     * @param tryInterval    多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     * @return true 获取成功，false获取失败
     */
    private boolean getLock(RedisLock lock, long timeout, long tryInterval, long lockExpireTime) {

        if (lock == null || StringUtils.isEmpty(lock.getKey()) || StringUtils.isEmpty(lock.getValue())) {
            return false;
        }

        try {
            long startTime = System.currentTimeMillis();
            do {
                if (!template.hasKey(lock.getKey())) {
                    ValueOperations<String, String> ops = template.opsForValue();
                    ops.set(lock.getKey(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                } else {// 存在锁
                    log.debug("lock is exist!！！");
                }
                // 尝试超过了设定值之后直接跳出循环
                if (System.currentTimeMillis() - startTime > timeout) {
                    return false;
                }
                Thread.sleep(tryInterval);
            } while (template.hasKey(lock.getKey()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void releaseLock(RedisLock lock) {
        if (!StringUtils.isEmpty(lock.getKey())) {
            template.delete(lock.getKey());
        }
    }

}
