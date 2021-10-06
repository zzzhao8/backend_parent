package com.mooc.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemaRespVO;
import com.mooc.meetingfilm.utils.common.vo.BasePageVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

import java.util.List;

/**
 * @date: 2021/8/7 9:12
 * @ClassName: CinemaServiceAPI
 * @description:
 */
public interface CinemaServiceAPI {

    //保存影院方法
    void saveCinema(CinemaSavedReqVO cinemaSavedReqVO) throws CommonServiceException;

    //查询影院
    IPage<DescribeCinemaRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException;

}
