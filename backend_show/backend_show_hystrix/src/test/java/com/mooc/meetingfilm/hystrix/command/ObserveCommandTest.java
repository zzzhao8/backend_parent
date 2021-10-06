package com.mooc.meetingfilm.hystrix.command;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

/**
 * @date: 2021/9/4 23:49
 * @ClassName: ObserveCommandTest
 * @description:
 */
public class ObserveCommandTest {

    @Test
    public void observeTest() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        ObserveCommandDemo commandDemo = new ObserveCommandDemo("ObserveCommandTest");
        Observable<String> observe = commandDemo.observe();

        // 阻塞式调用
        /*String result = observe.toBlocking().single();
        long endTime = System.currentTimeMillis();
        System.out.println("result=" + result + ", speeding=" + (endTime - beginTime));*/


        // 非阻塞式调用
        observe.subscribe(new Subscriber<String>(){
            @Override
            public void onCompleted() {
                System.out.println("ObserveCommandTest-observe , onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("ObserveCommandTest-observe , onError - throwable="+ e);
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.out.println("ObserveCommandTest-observe , onNext result=" + result + ", speeding=" + (endTime - beginTime));
            }
        });

        Thread.sleep(2000);
    }

}
