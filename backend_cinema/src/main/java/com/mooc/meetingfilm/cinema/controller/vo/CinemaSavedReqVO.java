package com.mooc.meetingfilm.cinema.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BaseRequestVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * @date: 2021/8/7 9:07
 * @ClassName: CinemaSavedReqVO
 * @description: 保存影院Vo对象
 */
@Data
public class CinemaSavedReqVO extends BaseRequestVO {


    private  String brandId;
    private  String areaId;
    private  String hallTypeId;
    private  String cinemaName;
    private  String cinemaAddress;
    private  String cinemaTele;
    private  String cinemaImgAddress;
    private  String cinemaPrice;


    @Override
    public void checkParam() throws CommonServiceException {

    }
}
