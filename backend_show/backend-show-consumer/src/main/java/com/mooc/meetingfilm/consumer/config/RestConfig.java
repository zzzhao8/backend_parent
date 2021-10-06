package com.mooc.meetingfilm.consumer.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.niws.loadbalancer.NIWSDiscoveryPing;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @date: 2021/7/20 23:12
 * @ClassName: RestConfig
 * @description:
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * ribbon负载均衡规则
     * @return
     */
    @Bean
    public IRule iRule(){
        return new RoundRobinRule(); //轮询
    }

    @Bean
    public IPing iPing(){
        return new NIWSDiscoveryPing();
    }
}
