package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.message.entity.po.UserMsg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【user_msg】的数据库操作Mapper
* @createDate 2024-11-21 15:06:53
* @Entity .UserMsg
*/
@Mapper
public interface UserMsgDao extends BaseMapper<UserMsg> {

}




