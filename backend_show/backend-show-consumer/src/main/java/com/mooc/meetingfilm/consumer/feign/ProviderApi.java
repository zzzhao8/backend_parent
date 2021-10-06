package com.mooc.meetingfilm.consumer.feign;

import com.mooc.meetingfilm.consumer.feign.vo.UserModel;
import com.mooc.meetingfilm.feignconf.FeignHelloConf;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @date: 2021/9/20 9:26
 * @ClassName: ProviderApi
 * @description:
 */

@FeignClient(name = "hello-service-provider",
        primary = true,
        path = "/provider",
        fallbackFactory = FallbackFactory.class
        //fallback = ProviderFallbackAPIImpl.class
        //configuration = FeignHelloConf.class,
        //url = "http://localhost:7101"
)
public interface ProviderApi {

    @RequestMapping(value = "/sayhello", method = RequestMethod.GET)
    String invokerProviderController(@RequestParam("message") String message);

//    @RequestLine("GET /sayhello?message={message}")
//    String invokerProviderController(@Param("message") String message);

//
//    @RequestMapping(value = "/{providerId}/sayhello", method = RequestMethod.POST)
//    String postProvider(
//            @RequestHeader("author") String author,
//            @PathVariable("providerId") String providerId,
//            @RequestBody UserModel userModel
//    );
}
