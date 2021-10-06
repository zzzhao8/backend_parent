package com.mooc.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mooc.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;

/**
 * @date: 2021/8/1 10:41
 * @ClassName: FilmServiceAPI
 * @description: 影片逻辑层
 */
public interface FilmServiceAPI {

    //获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException;

    //获取影片列表
    IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException;

    //根据主键获取影片信息
    DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException;


    //保存电影信息
    void saveFilm(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException;

}
