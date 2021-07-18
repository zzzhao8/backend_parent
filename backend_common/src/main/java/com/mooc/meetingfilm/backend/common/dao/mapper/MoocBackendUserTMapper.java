package com.mooc.meetingfilm.backend.common.dao.mapper;

import com.mooc.meetingfilm.backend.common.dao.entity.MoocBackendUserT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author mooc
 * @since 2021-07-18
 */
public interface MoocBackendUserTMapper extends BaseMapper<MoocBackendUserT> {

    List<MoocBackendUserT> describeUserByUserName(@Param("username") String username);

}
