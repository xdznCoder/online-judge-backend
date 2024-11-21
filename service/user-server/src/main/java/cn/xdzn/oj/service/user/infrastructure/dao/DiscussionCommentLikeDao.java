package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionCommentLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【discussion_comment_like】的数据库操作Mapper
* @createDate 2024-11-21 11:01:10
* @Entity .DiscussionCommentLike
*/
@Mapper
public interface DiscussionCommentLikeDao extends BaseMapper<DiscussionCommentLike> {

}




