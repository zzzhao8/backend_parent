package com.mooc.meetingfilm.film.controller.vo;

import lombok.Data;

/**
 * @date: 2021/8/1 10:30
 * @ClassName: DescribeActorsRespVO
 * @description: 获取影片列表VO对象
 */
@Data
public class DescribeFilmsRespVO {

    private String filmId;
    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String filmScore;
    private String preSaleNum;
    private String boxOffice;
    private String filmTime;
    private String filmLength;
    private String mainImg;

}
