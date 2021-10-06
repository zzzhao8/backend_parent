package com.mooc.meetingfilm.consumer.feign;

import org.springframework.stereotype.Service;

/**
 * @date: 2021/10/6 15:49
 * @ClassName: ProviderFallbackAPIImpl
 * @description: 降级实现
 */
@Service
public class ProviderFallbackAPIImpl implements ProviderApi {

    @Override
    public String invokerProviderController(String message) {
        return "invokerProviderController fallback message=" + message;
    }
}
