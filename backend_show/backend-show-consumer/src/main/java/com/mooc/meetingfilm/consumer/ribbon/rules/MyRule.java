package com.mooc.meetingfilm.consumer.ribbon.rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;

/**
 * @date: 2021/8/15 21:13
 * @ClassName: MyRule
 * @description:
 */
public class MyRule extends AbstractLoadBalancerRule {

    public MyRule(){

    }
    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {

        //灰度发布 10%的流量在新的功能，~ 90%的流量在旧的功能
        // 对10%有没有限制，已经访问过新功能的流量不能再访问旧的功能

        //定义一个新功能的ServerList
        // up all

        // 每次请求进入，判断对应的客户端是不是已经访问过新的ServerList
            // 如果是 ，则直接访问新的ServerList
            // 如果不是，则继续后续判断

        // 引入缓存， 十个请求里随机取2-3个请求进入新功能的serverList 【redis】



        //无论传什么都返回null
        return null;
    }
}
