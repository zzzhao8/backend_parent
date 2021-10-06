package com.mooc.meetingfilm.hall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.hall.controller.vo.HallSaveReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsReqVO;
import com.mooc.meetingfilm.hall.controller.vo.HallsRespVO;
import com.mooc.meetingfilm.hall.service.HallServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BaseRequestVO;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @date: 2021/8/7 23:10
 * @ClassName: HallController
 * @description:
 */
@RequestMapping(value = "/halls")
@RestController
public class HallController {

    @Autowired
    private HallServiceAPI hallServiceAPI;

    /**
     * 获取播放厅列表
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVO describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        hallsReqVO.checkParam();

        IPage<HallsRespVO> hallsRespVOIPage = hallServiceAPI.describeHalls(hallsReqVO);
        Map<String, Object> halls = describePageResult(hallsRespVOIPage, "halls");

        return BaseResponseVO.success(halls);
    }


    @RequestMapping(value = "/hall:add", method = RequestMethod.POST)
    public BaseResponseVO addHall(@RequestBody HallSaveReqVO hallSaveReqVO) throws CommonServiceException {

        hallSaveReqVO.checkParam();
        hallServiceAPI.addHall(hallSaveReqVO);

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
