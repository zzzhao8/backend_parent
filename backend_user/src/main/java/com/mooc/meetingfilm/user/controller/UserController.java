package com.mooc.meetingfilm.user.controller;

import com.mooc.meetingfilm.user.controller.vo.LoginReqVO;
import com.mooc.meetingfilm.user.service.UserServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserServiceAPI userserviceapi;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponseVO login(@RequestBody LoginReqVO reqVO)throws CommonServiceException{

        // 数据验证
        reqVO.checkParam();

        // 验证用户名和密码是否正确
        String userId = userserviceapi.checkUserLogin(reqVO.getUsername(), reqVO.getPassword());

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(userId, randomKey);
        /**
         *  创建时间
         *  过期时间
         *  randomKey  - jwt数据签名：AES -> 源数据 + 盐 -> 在token中解析出randomkey -> 数据验签
         *  userId  -  用户身份认证
         */

        //randomKey token
        HashMap<String, String> result = new HashMap<>();
        result.put("randomKey", randomKey);
        result.put("token", token);

        return BaseResponseVO.success(result);
    }
}
