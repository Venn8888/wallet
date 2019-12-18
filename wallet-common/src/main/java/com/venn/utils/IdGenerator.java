package com.venn.utils;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/12/18
 */
public class IdGenerator {

    private long workerId;
    private long dataCenterId;
    private long sequence = 0L;
    /**
     * 唯一时间，这是一个避免重复的随机量，自行设定不要大于当前时间戳
     */
    private static final long TWEPOCH = 1288834974657L;
    /**
     * Thu, 04 Nov 2010 01:42:54 GMT
     */
    private long workerIdBits = 5L;
    /**
     * 节点ID长度
     */
    private long datacenterIdBits = 5L;
    /**
     * 最大支持数据中心节点数0~31，一共32个
     */
    private long sequenceBits = 12L;
    /**
     * 序列号12位
     */
    private long workerIdShift = sequenceBits;
    /**
     * 机器节点左移12位
     */
    private long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 数据中心节点左移17位
     */
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     * 时间毫秒数左移22位
     */
    private long sequenceMask = ~(-1L << sequenceBits);
    /**
     * 最大为4095
     */
    private long lastTimestamp = -1L;

    private static class IdGeneratorHolder {
        private static final IdGenerator INSTANCE = new IdGenerator();
    }

    private static IdGenerator get() {
        return IdGeneratorHolder.INSTANCE;
    }

    private IdGenerator() {
        this(0L, 0L);
    }

    private IdGenerator(long workerId, long dataCenterId) {
        //数据中心ID长度
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        //最大支持机器节点数0~31，一共32个
        long maxDataCenterId = ~(-1L << datacenterIdBits);
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    private synchronized long nextId() {
        long timestamp = timeGen();
        //获取当前毫秒数
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
                //自旋等待到下一毫秒
            }
        } else {
            sequence = 0L;
            //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;
        return ((timestamp - TWEPOCH) << timestampLeftShift) | (dataCenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println(IdGenerator.get().nextId());
    }
}
