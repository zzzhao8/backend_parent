package com.mooc.meetingfilm.hystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @date: 2021/9/5 9:24
 * @ClassName: CommandCollapserTest
 * @description: 请求合并测试
 */
public class CommandCollapserTest {

    @Test
    public void CommandCollapserTest() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        //构建请求
        CommandCollapser c1 = new CommandCollapser(1);
        CommandCollapser c2 = new CommandCollapser(2);
        CommandCollapser c3 = new CommandCollapser(3);
        CommandCollapser c4 = new CommandCollapser(4);

        // 获取结果, 足够的近 10ms
        Future<String> q1 = c1.queue();
        Future<String> q2 = c2.queue();
        Future<String> q3 = c3.queue();
        Future<String> q4 = c4.queue();

        String r1 = q1.get();
        String r2 = q2.get();
        String r3 = q3.get();
        String r4 = q4.get();

        System.err.println(r1 + ", "+ r2+ ", "+ r3+ ", "+ r4);

        context.close();
    }

}
