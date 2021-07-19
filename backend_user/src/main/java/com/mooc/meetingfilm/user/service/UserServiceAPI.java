package com.mooc.meetingfilm.user.service;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;

/**
* @Author: zzl
* @Date: 2021/7/18
* @Param:
* @return:
* @Description: 用户接口
*/
public interface UserServiceAPI {

    String checkUserLogin(String username, String password) throws CommonServiceException;
}
