package com.cy.sys.log;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

class BeanTest {

    private BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10); // 工作队列(阻塞)
    // 线程工厂对象
    private ThreadFactory threadFactory = new ThreadFactory() {
        private AtomicLong at = new AtomicLong(1); // 线程安全的long类型数据
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"DB-thread-pool-"+at.incrementAndGet());
        }
    };


    @Test
    void test() {


        int corePoolSize = 1;  // 线程核心数
        int maximumPoolSize = 10; // 最大线程数
        long keepAliveTime = 30L; // 线程空闲时间
        TimeUnit timeUnit = TimeUnit.SECONDS; // 线程空闲时间单位

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, timeUnit, blockingQueue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
