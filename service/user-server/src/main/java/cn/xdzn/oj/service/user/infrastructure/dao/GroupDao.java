package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 团队Dao层
* @author Shelly6
* @description 针对表【group】的数据库操作Mapper
* @createDate 2024-10-28 13:34:48
* @Entity .Group
*/
@Mapper
public interface GroupDao extends BaseMapper<Group> {

}

