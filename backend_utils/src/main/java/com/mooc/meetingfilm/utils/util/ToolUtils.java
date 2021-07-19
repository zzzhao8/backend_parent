package com.mooc.meetingfilm.utils.util;

import org.springframework.util.StringUtils;

/**
 * @ClassName: ToolUtils
 * @description: 基础工具类
 * @date: 2021/7/18 19:39
 */
public class ToolUtils {

    private ToolUtils(){}

    /**
    * @Description: 字符串为空
    * @Param: [str]
    * @return: boolean
    * @Author: ManolinCoder
    * @Date: 2021/7/18
    */
    public static boolean strIsNull(String str){
        return StringUtils.isEmpty(str);
    }

    /**
    * @Description: 字符串不为空
    * @Param: [str]
    * @return: boolean
    * @Author: ManolinCoder
    * @Date: 2021/7/18
    */
    public static boolean strIsNotNull(String str){
        return !StringUtils.isEmpty(str);
    }
}
