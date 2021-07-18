package service.impl;

import com.mooc.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.mooc.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import service.IMoocBackendUserTService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author mooc
 * @since 2021-07-18
 */
@Service
public class MoocBackendUserTServiceImpl extends ServiceImpl<MoocBackendUserTMapper, MoocBackendUserT> implements IMoocBackendUserTService {

}
