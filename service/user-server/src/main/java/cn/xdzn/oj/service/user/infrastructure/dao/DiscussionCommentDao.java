package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【discussion_comment】的数据库操作Mapper
* @createDate 2024-10-30 20:19:09
* @Entity .DiscussionComment
*/
@Mapper
public interface DiscussionCommentDao extends BaseMapper<DiscussionComment> {

}




