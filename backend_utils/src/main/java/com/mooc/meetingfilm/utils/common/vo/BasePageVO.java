package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

/**
 * @date: 2021/8/1 10:16
 * @ClassName: BasePageVo
 * @description: 分页请求类
 */
@Data
public class BasePageVO extends BaseRequestVO {
     private Integer nowPage = 1;
     private Integer pageSize = 10;


    @Override
    public void checkParam() throws CommonServiceException {
        // nowPage 和 pageSize 不能为空
        if (ToolUtils.strIsNull(nowPage.toString()) || ToolUtils.strIsNull(pageSize.toString())){
            throw new CommonServiceException(404, "nowPage 或者 pageSize不能为空");
        }
    }
}
