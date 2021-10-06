package com.mooc.meetingfilm.consumer.feign;

import com.mooc.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.stereotype.Service;

/**
 * @date: 2021/10/5 9:32
 * @ClassName: ProviderApiImpl
 * @description:
 */
@Service
public class ProviderApiImpl implements ProviderApi {

    @Override
    public String invokerProviderController(String message) {
        return null;
    }

//    @Override
//    public String postProvider(String author, String providerId, UserModel userModel) {
//        return null;
//    }
}
