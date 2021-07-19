package com.mooc.meetingfilm.user.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BaseRequestVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

@Data
public class LoginReqVO extends BaseRequestVO {
    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        // TODO 验证数据合法性；
        if (ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)){
            throw new CommonServiceException(404, "username 或者 password不能为空");
        }
    }
}
