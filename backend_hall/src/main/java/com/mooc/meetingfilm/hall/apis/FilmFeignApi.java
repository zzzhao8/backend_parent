package com.mooc.meetingfilm.hall.apis;

import com.mooc.meetingfilm.apis.film.FilmFeignApis;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * film提供的接口服务
 */
@FeignClient(name = "film-service")
public interface FilmFeignApi extends FilmFeignApis {

}
