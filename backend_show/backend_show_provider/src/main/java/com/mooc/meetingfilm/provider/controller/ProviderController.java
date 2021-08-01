package com.mooc.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @date: 2021/7/20 23:01
 * @ClassName: ProviderController
 * @description:
 */
@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/provider/sayhello")
    public String providerSayHello(String message){
        log.error("provider sayhello port: {}, message: {}", port, message);
        return "provider sayhello port:" +port+" , message: "+message;
    }
}
