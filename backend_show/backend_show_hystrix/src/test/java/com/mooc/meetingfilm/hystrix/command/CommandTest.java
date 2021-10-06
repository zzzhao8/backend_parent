package com.mooc.meetingfilm.hystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @date: 2021/9/4 22:42
 * @ClassName: CommandTest
 * @description:
 */
public class CommandTest {

    @Test
    public void executeTest() {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("execute");

        // 同步执行Command
        String result = commandDemo.execute();

        long endTime = System.currentTimeMillis();

        System.out.println("result=" + result + ", speeding=" + (endTime - beginTime));

    }

    @Test
    public void queueTest() throws ExecutionException, InterruptedException {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("queue");

        // 异步执行Command
        Future<String> queue = commandDemo.queue();

        long endTime = System.currentTimeMillis();
        System.out.println("future end, speeding=" + (endTime - beginTime));

        long endTime2 = System.currentTimeMillis();
        System.out.println("result=" + queue.get() + ", speeding=" + (endTime2 - beginTime));

    }

    @Test
    public void observeTest() {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo = new CommandDemo("observe");

        Observable<String> observe = commandDemo.observe();

        // 阻塞式调用
        String result = observe.toBlocking().single();
        long endTime = System.currentTimeMillis();
        System.out.println("result=" + result + ", speeding=" + (endTime - beginTime));


        // 非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("observe , onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("observe , onError - throwable=" + e);
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("observe , onNext result=" + result + ", speeding=" + (endTime - beginTime));
            }
        });
    }


    @Test
    public void toObservableTest() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        CommandDemo commandDemo1 = new CommandDemo("toObservable");
        Observable<String> toObservable1 = commandDemo1.toObservable();
        // 阻塞式调用
        String result = toObservable1.toBlocking().single();
        long endTime = System.currentTimeMillis();
        System.out.println("result=" + result + ", speeding=" + (endTime - beginTime));

        CommandDemo commandDemo2 = new CommandDemo("toObservable");
        Observable<String> toObservable2 = commandDemo2.toObservable();
        // 非阻塞式调用
        toObservable2.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("toObservable , onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("toObservable , onError - throwable=" + e);
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("toObservable , onNext result=" + result + ", speeding=" + (endTime - beginTime));
            }
        });
        Thread.sleep(2000);
    }


    /**
     * 请求缓存
     */
    @Test
    public void requestCache() {
        //开启请求上下文
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();

        long beginTime = System.currentTimeMillis();
        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c1");

        // 第一次请求
        String r1 = c1.execute();
        System.out.println("result=" + r1 + ", speeding=" + (System.currentTimeMillis() - beginTime));
        // 第2次请求
        String r2 = c2.execute();
        System.out.println("result=" + r2 + ", speeding=" + (System.currentTimeMillis() - beginTime));
        // 第3次请求
        String r3 = c3.execute();
        System.out.println("result=" + r3 + ", speeding=" + (System.currentTimeMillis() - beginTime));

        //请求上下文关闭
        requestContext.close();
    }


    /**
     * 演示线程池内容
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void threadTest() throws ExecutionException, InterruptedException {

        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c3");
        CommandDemo c4 = new CommandDemo("c4");
        CommandDemo c5 = new CommandDemo("c5");

        Future<String> q1 = c1.queue();
        Future<String> q2 = c2.queue();
        Future<String> q3 = c3.queue();
        Future<String> q4 = c4.queue();
        Future<String> q5 = c5.queue();

        String s1 = q1.get();
        String s2 = q2.get();
        String s3 = q3.get();
        String s4 = q4.get();
        String s5 = q5.get();


        System.out.println(s1+", "+s2+", "+s3+", "+s4+", "+s5);
    }
}




















