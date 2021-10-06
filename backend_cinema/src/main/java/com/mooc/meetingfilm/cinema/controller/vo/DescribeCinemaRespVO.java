package com.mooc.meetingfilm.cinema.controller.vo;

import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import lombok.Data;

/**
 * @date: 2021/8/7 9:17
 * @ClassName: DescribeCinemaRespVO
 * @description:
 */
@Data
public class DescribeCinemaRespVO{

     private String brandId;
     private String areaId;
     private String hallTypeIds;
     private String cinemaName;
     private String cinemaAddress;
     private String cinemaTele;
     private String cinemaImgAddress;
     private String cinemaPrice;



}
