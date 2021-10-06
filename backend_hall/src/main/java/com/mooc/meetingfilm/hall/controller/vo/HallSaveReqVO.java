package com.mooc.meetingfilm.hall.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BaseRequestVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * @date: 2021/8/7 23:29
 * @ClassName: HallSaveReqVO
 * @description:
 */
@Data
public class HallSaveReqVO extends BaseRequestVO {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
