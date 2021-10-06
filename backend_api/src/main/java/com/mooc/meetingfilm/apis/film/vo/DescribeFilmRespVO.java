package com.mooc.meetingfilm.apis.film.vo;

import lombok.Data;

/**
 * @date: 2021/8/1 10:30
 * @ClassName: DescribeActorsRespVO
 * @description: 根据主键获取影片信息VO对象
 */
@Data
public class DescribeFilmRespVO {

    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;

}
