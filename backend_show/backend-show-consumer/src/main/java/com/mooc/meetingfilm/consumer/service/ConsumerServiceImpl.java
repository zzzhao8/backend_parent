package com.mooc.meetingfilm.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @date: 2021/7/20 23:08
 * @ClassName: ConsumerServiceImpl
 * @description:
 */
@Service
public class ConsumerServiceImpl implements ConsumerServiceAPI {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String sayHello(String message) {
        // 准备工作
//        String hostname = "localhost";
//        int port = 8101;

        // get register
//        ServiceInstance choose = eurekaClient.choose("hello-service-provider");
//        String hostname = choose.getHost();
//        int port = choose.getPort();


        String uri = "/provider/sayhello?message=" + message;

        String url = "http://hello-service-provider" + uri;
        // invoker provider test
        String result = restTemplate.getForObject(url, String.class);

        return result;
    }
}
