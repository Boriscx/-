package com.cy.pj.sys.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class TaskThreadPool {

    private ThreadPoolExecutor threadPoolExecutor;

    public TaskThreadPool(TaskExecutionProperties properties) {

        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicLong atomicLong = new AtomicLong(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, properties.getThreadNamePrefix() + atomicLong.incrementAndGet());
            }
        };
        threadPoolExecutor = new ThreadPoolExecutor(properties.getPool().getCoreSize(),
                properties.getPool().getMaxSize(),
                properties.getPool().getKeepAlive().getSeconds(), TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(properties.getPool().getQueueCapacity()),
                threadFactory
        );
    }

    public void setExecutor(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

}
