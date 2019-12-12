package com.cy.pj.sys.util;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Setter
@Configuration
@ConfigurationProperties("db-thread-pool")
public class SpringAsyncConfig implements AsyncConfigurer {

    private int corePoolSize = 8;

    private int maxNumPoolSize = Integer.MAX_VALUE;

    private int queueCapacity = Integer.MAX_VALUE;

    private int keepAliveTime = 60;

    private ThreadFactory threadFactory = new ThreadFactory() {
        private AtomicLong at = new AtomicLong(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "db-thread-pool-task-" + at.getAndIncrement());
        }
    };

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                log.warn("线程池执行中发生了异常!",throwable);
            }
        };
    }

    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxNumPoolSize);
        taskExecutor.setKeepAliveSeconds(keepAliveTime);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadFactory(threadFactory);
        taskExecutor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor exe) -> {
            log.warn("线程队列已满");
        });
        taskExecutor.initialize();
        return taskExecutor;
    }
}
