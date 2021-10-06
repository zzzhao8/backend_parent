package com.mooc.meetingfilm.hall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mooc.meetingfilm.hall.controller.vo.HallSaveReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.dao.entity.MoocFieldT;
import com.mooc.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.mooc.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.mooc.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.mooc.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @date: 2021/8/7 23:42
 * @ClassName: HallServiceImpl
 * @description:
 */
@Service
public class HallServiceImpl implements HallServiceAPI{

    @Resource
    private MoocFieldTMapper moocFieldTMapper;

    @Resource
    private MoocHallFilmInfoTMapper moocHallFilmInfoTMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        Page<HallsReqVO> page = new Page<>(hallsReqVO.getNowPage(), hallsReqVO.getPageSize());

        QueryWrapper<HallsReqVO> reqVOQueryWrapper = new QueryWrapper<>();
        if (ToolUtils.strIsNotNull(hallsReqVO.getCinemaId())) {
            reqVOQueryWrapper.eq("cinema_id", hallsReqVO.getCinemaId());
        }

        IPage<HallsRespVO> hallsRespVOIPage = moocFieldTMapper.describeHalls(page, reqVOQueryWrapper);

        return hallsRespVOIPage;
    }

    @Override
    @Transactional
    public void addHall(HallSaveReqVO reqVO) throws CommonServiceException {

        //播放厅的列表数据
        MoocFieldT field = new MoocFieldT();
        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));


        // 播放厅对应的影片数据，影片冗余数据，便于查询，缓存
        MoocHallFilmInfoT filmInfo = describeFilmInfo(reqVO.getFilmId());

        moocFieldTMapper.insert(field);
        moocHallFilmInfoTMapper.insert(filmInfo);

    }


    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException{

        // get register
        ServiceInstance choose = eurekaClient.choose("film-service");
        //组织调用参数
        String hostName = choose.getHost();
        int port = choose.getPort();
        String uri = "/films/"+filmId;
        String url = "http://"+hostName+":"+port+uri;
        //通过restTemplate调用影片服务
        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);

        //解析返回值
        if(baseResponseVO == null){
            throw new CommonServiceException(404, "获取数据失败");
        }
        JSONObject dataJson = baseResponseVO.getJSONObject("data");

        //组织参数
        MoocHallFilmInfoT filmInfo = new MoocHallFilmInfoT();

        filmInfo.setFilmId(dataJson.getIntValue("filmId"));
        filmInfo.setFilmName(dataJson.getString("filmName"));
        filmInfo.setFilmLength(dataJson.getString("filmLength"));
        filmInfo.setFilmCats(dataJson.getString("filmCats"));
        filmInfo.setActors(dataJson.getString("actors"));
        filmInfo.setImgAddress(dataJson.getString("imgAddress"));

        return filmInfo;
    }


}
