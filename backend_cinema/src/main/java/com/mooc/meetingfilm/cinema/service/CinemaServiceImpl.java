package com.mooc.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemaRespVO;
import com.mooc.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.mooc.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2021/8/7 9:31
 * @ClassName: CinemaServiceImpl
 * @description:
 */
@Service
public class CinemaServiceImpl implements CinemaServiceAPI {

    @Resource
    private MoocCinemaTMapper cinemaTMapper;

    @Override
    public void saveCinema(CinemaSavedReqVO cinemaSavedReqVO) throws CommonServiceException {

        MoocCinemaT cinema = new MoocCinemaT();
        cinema.setCinemaName(cinemaSavedReqVO.getCinemaName());
        cinema.setCinemaPhone(cinemaSavedReqVO.getCinemaTele());
        cinema.setBrandId(ToolUtils.str2Int(cinemaSavedReqVO.getBrandId()));
        cinema.setAreaId(ToolUtils.str2Int(cinemaSavedReqVO.getAreaId()));
        cinema.setHallIds(cinemaSavedReqVO.getHallTypeId());
        cinema.setImgAddress(cinemaSavedReqVO.getCinemaImgAddress());
        cinema.setCinemaAddress(cinemaSavedReqVO.getCinemaAddress());
        cinema.setMinimumPrice(ToolUtils.str2Int(cinemaSavedReqVO.getCinemaPrice()));

        cinemaTMapper.insert(cinema);

    }

    @Override
    public IPage<DescribeCinemaRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException {

        Page<MoocCinemaT> page = new Page<>(nowPage, pageSize);
        IPage<MoocCinemaT> cinemaIPage = cinemaTMapper.selectPage(page, null);
        List<MoocCinemaT> records = cinemaIPage.getRecords();
        IPage<DescribeCinemaRespVO> respVOIPage = new Page<>();
        List<DescribeCinemaRespVO> describeCinemaRespVOS = new ArrayList<>();
        records.forEach(moocCinemaT -> {
            DescribeCinemaRespVO describeCinemaRespVO = new DescribeCinemaRespVO();
            describeCinemaRespVO.setBrandId(moocCinemaT.getUuid().toString());
            describeCinemaRespVO.setAreaId(moocCinemaT.getAreaId().toString());
            describeCinemaRespVO.setHallTypeIds(moocCinemaT.getHallIds());
            describeCinemaRespVO.setCinemaName(moocCinemaT.getCinemaName());
            describeCinemaRespVO.setCinemaAddress(moocCinemaT.getCinemaAddress());
            describeCinemaRespVO.setCinemaTele(moocCinemaT.getCinemaPhone());
            describeCinemaRespVO.setCinemaImgAddress(moocCinemaT.getImgAddress());
            describeCinemaRespVO.setCinemaPrice(moocCinemaT.getMinimumPrice().toString());
            describeCinemaRespVOS.add(describeCinemaRespVO);
        });
        respVOIPage.setRecords(describeCinemaRespVOS);
        respVOIPage.setTotal(describeCinemaRespVOS.size());
        return respVOIPage;
    }
}
