package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【discussion_like】的数据库操作Mapper
* @createDate 2024-11-21 11:01:10
* @Entity .DiscussionLike
*/
@Mapper
public interface DiscussionLikeDao extends BaseMapper<DiscussionLike> {

}




