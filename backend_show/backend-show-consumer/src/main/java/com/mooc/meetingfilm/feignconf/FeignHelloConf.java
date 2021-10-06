package com.mooc.meetingfilm.feignconf;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @date: 2021/10/5 11:40
 * @ClassName: FeignHelloConf
 * @description:
 */
@Configuration
public class FeignHelloConf {

    @Bean
    public Contract contract(){
        return new feign.Contract.Default();
    }

}
