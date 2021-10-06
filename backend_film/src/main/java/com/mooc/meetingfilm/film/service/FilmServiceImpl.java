package com.mooc.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.mooc.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.mooc.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmActorT;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmInfoT;
import com.mooc.meetingfilm.film.dao.entity.MoocFilmT;
import com.mooc.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.mooc.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.mooc.meetingfilm.utils.util.ToolUtils.str2Int;
import static com.mooc.meetingfilm.utils.util.ToolUtils.str2LocalDateTime;

/**
 * @date: 2021/8/1 10:42
 * @ClassName: FilmServiceImpl
 * @description: 影片模块实现层
 */
@Service
public class FilmServiceImpl implements FilmServiceAPI {

    @Resource
    private MoocActorTMapper actorTMapper;

    @Resource
    private MoocFilmActorTMapper filmActorTMapper;

    @Resource
    private MoocFilmInfoTMapper filmInfoTMapper;

    @Resource
    private MoocFilmTMapper filmTMapper;

    /**
     * 演员查询列表
     *
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException {

        // 查询演员列表
        return actorTMapper.describeActors(new Page<>(nowPage, pageSize));

    }

    /**
     * 影片列表查询
     *
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException {
        return filmTMapper.describeFilms(new Page<>(nowPage, pageSize));
    }

    /**
     * 根据主键获取电影详情
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {

        return filmTMapper.describeFilmById(filmId);
    }

    /**
     * 保存电影信息
     *
     * @param reqVO
     * @throws CommonServiceException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFilm(FilmSavedReqVO reqVO) throws CommonServiceException {
        try {
            //保存电影主表
            MoocFilmT film = new MoocFilmT();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());
            film.setFilmPresalenum(str2Int(reqVO.getPreSaleNum()));
            film.setFilmBoxOffice(str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(str2Int(reqVO.getAreaId()));
            film.setFilmDate(str2Int(reqVO.getDateId()));
            film.setFilmTime(str2LocalDateTime(reqVO.getFilmTime()+" 00:00:00"));
            film.setFilmStatus(str2Int(reqVO.getFilmStatus()));
            filmTMapper.insert(film);

            //保存电影子表
            MoocFilmInfoT filmInfo = new MoocFilmInfoT();
            filmInfo.setFilmId(film.getUuid().toString());
            filmInfo.setFilmEnName(reqVO.getFilmEnName());
            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(str2Int(reqVO.getFilmScore()));
            filmInfo.setFilmLength(str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());
            filmInfoTMapper.insert(filmInfo);


            String[] actIds = reqVO.getActIds().split("#");
            String[] roleNames = reqVO.getRoleNames().split("#");
            if (actIds.length != roleNames.length){
                throw new CommonServiceException(500, "演员和角色数量不匹配");
            }
            for (int i = 0; i < actIds.length; i++) {
                //保存演员映射表
                MoocFilmActorT filmActor = new MoocFilmActorT();
                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(str2Int(actIds[i]));
                filmActor.setRoleName(roleNames[i]);
                filmActorTMapper.insert(filmActor);
            }




        } catch (Exception e) {
            throw new CommonServiceException(500, e.getMessage());
        }
    }
}
