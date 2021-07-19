package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.jboss.logging.Param;

/**
 * @ClassName: BaseRequestVO
 * @description: 公共的请求对象
 * @date: 2021/7/18 19:16
 */
public abstract class BaseRequestVO {


    /**
     * @Description: 公共的参数验证方法
     * @Param: []
     * @return: void
     * @Author: ManolinCoder
     * @Date: 2021/7/18
     */
    public abstract void checkParam() throws CommonServiceException;

}
