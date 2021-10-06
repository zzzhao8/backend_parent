package com.mooc.meetingfilm.hall.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @date: 2021/8/8 16:34
 * @ClassName: RestConf
 * @description:
 */
@Configuration
public class RestConf {

    @Bean
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }


}
