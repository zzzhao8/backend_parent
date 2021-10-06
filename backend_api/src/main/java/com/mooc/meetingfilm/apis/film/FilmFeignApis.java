package com.mooc.meetingfilm.apis.film;

import com.mooc.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @date: 2021/10/6 23:06
 * @ClassName: FilmFeignApis
 * @description: Film提供的公共接口服务
 */
public interface FilmFeignApis {


    /**
     * 对外暴露的接口服务 --- 根据主键获取影片信息
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/films/{filmId}", method = RequestMethod.GET)
    BaseResponseVO<DescribeFilmRespVO> describeFilmsById(@PathVariable("filmId") String filmId) throws CommonServiceException;

}
