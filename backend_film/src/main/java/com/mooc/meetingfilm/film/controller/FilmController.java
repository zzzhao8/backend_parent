package com.mooc.meetingfilm.film.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.mooc.meetingfilm.film.service.FilmServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BasePageVO;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2021/8/1 10:12
 * @ClassName: FilmController
 * @description: 影片模块表现层
 */
@RestController
@RequestMapping(value = "/films")
public class FilmController {

    @Autowired
    private FilmServiceAPI filmServiceAPI;

    /** 获取演员列表 */
    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public BaseResponseVO describeActors(BasePageVO basePageVo) throws CommonServiceException {
        //检查入参
        basePageVo.checkParam();

        //调用逻辑层, 获取返回参数
        IPage<DescribeActorsRespVO> results = filmServiceAPI.describeActors(basePageVo.getNowPage(), basePageVo.getPageSize());

        //组织返回层

        return BaseResponseVO.success(describePageResult(results, "actors"));
    }

    /** 获取影片列表 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVO describeFilms(BasePageVO basePageVo) throws CommonServiceException {
        //检查入参
        basePageVo.checkParam();

        //调用逻辑层, 获取返回参数
        IPage<DescribeFilmsRespVO> results = filmServiceAPI.describeFilms(basePageVo.getNowPage(), basePageVo.getPageSize());

        //组织返回层

        return BaseResponseVO.success(describePageResult(results, "films"));
    }

    /** 根据主键获取影片信息 */
    @RequestMapping(value = "/{filmId}", method = RequestMethod.GET)
    public BaseResponseVO describeFilmsById(@PathVariable("filmId") String filmId) throws CommonServiceException {

        //调用逻辑层, 获取返回参数
        DescribeFilmRespVO film = filmServiceAPI.describeFilmById(filmId);
        if (film == null) {
            return BaseResponseVO.success();
        }else {
            return BaseResponseVO.success(film);
        }
    }

    /** 新增影片 */
    @RequestMapping(value = "/film:add", method = RequestMethod.POST)
    public BaseResponseVO saveFilmInfo(@RequestBody FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {

        filmServiceAPI.saveFilm(filmSavedReqVO);

        return BaseResponseVO.success();

    }


    /** 获取分页对象的公共接口 */
    private Map<String, Object> describePageResult(IPage page, String title){
        Map<String, Object> result = Maps.newHashMap();
        result.put("totalPages", page.getPages());
        result.put("totalSize", page.getTotal());
        result.put("pageSize", page.getSize());
        result.put("nowPage", page.getCurrent());

        result.put(title, page.getRecords());
        return result;
    }

}
