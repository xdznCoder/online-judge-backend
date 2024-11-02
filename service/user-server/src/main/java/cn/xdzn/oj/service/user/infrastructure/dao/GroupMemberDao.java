package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【group_member】的数据库操作Mapper
* @createDate 2024-11-01 22:01:18
* @Entity .cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember
*/
@Mapper
public interface GroupMemberDao extends BaseMapper<GroupMember> {

}




