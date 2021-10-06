package com.mooc.meetingfilm.consumer.feign;

import org.springframework.stereotype.Service;

/**
 * @date: 2021/10/6 16:31
 * @ClassName: FallbackFactory
 * @description:
 */
@Service
public class FallbackFactory implements feign.hystrix.FallbackFactory {

    @Override
    public Object create(Throwable cause) {
        return new ProviderApi() {
            @Override
            public String invokerProviderController(String message) {
                return "invokerProviderController FallbackFactory message=" + message;
            }
        };
    }
}
