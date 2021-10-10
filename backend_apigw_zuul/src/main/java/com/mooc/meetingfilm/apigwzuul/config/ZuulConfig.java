package com.mooc.meetingfilm.apigwzuul.config;

import com.mooc.meetingfilm.apigwzuul.filters.JWTFilter;
import com.mooc.meetingfilm.apigwzuul.filters.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2021/10/7 7:57
 * @ClassName: ZuulConfig
 * @description:
 */
@Configuration
public class ZuulConfig {

    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }

    @Bean
    public JWTFilter initJWTFilter(){
        return new JWTFilter();
    }

}
