package com.mooc.meetingfilm.hystrix.command;

import com.netflix.hystrix.*;
import lombok.Data;

/**
 * @date: 2021/9/4 22:24
 * @ClassName: CommandDemo
 * @description:
 */
@Data
public class CommandDemo extends HystrixCommand<String> {

    private String name;

    public CommandDemo(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                        .withRequestCacheEnabled(false) //请求缓存开关
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE) //指定使用信号量
                        //.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD) //指定使用线程池
                ).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("MyThreadPool"))
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.defaultSetter().withCoreSize(2)
                                .withMaximumSize(3).withAllowMaximumSizeToDivergeFromCoreSize(true)
                                .withMaxQueueSize(5)
                )

        );

        this.name = name;
    }

    // 单次请求调用的业务方法
    @Override
    protected String run() throws Exception {
        String result = "CommandHelloWorld name: "+name;

        Thread.sleep(300L);

        System.err.println(result + " , currentThread-"+Thread.currentThread().getName());

        return result;

    }

    @Override
    protected String getCacheKey(){
        return String.valueOf(name);
    }
}
