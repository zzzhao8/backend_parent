package com.mooc.meetingfilm.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


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

    @RequestMapping(value = "/provider/sayhello", method = RequestMethod.GET)
    public String providerSayHello(String message){
        log.error("provider sayhello port: {}, message: {}", port, message);
        return "provider sayhello port:" +port+" , message: "+message;
    }

    @RequestMapping(value = "/provider/{providerId}/sayhello", method = RequestMethod.POST)
    public String postTest(
            @RequestHeader("author") String author,
            @PathVariable("providerId") String providerId,
            @RequestBody  String json
    ){
        log.error("provider sayhello port: {}, author:{}, providerId:{}, message: {}", port, author, providerId, json);
        return "provider sayhello port:" +port+" , message: "+json;
    }

}
