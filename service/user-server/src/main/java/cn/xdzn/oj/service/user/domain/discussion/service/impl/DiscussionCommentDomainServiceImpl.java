package cn.xdzn.oj.service.user.domain.discussion.service.impl;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionComment;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionCommentDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.DiscussionCommentDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【discussion_comment】的数据库操作Service实现
* @createDate 2024-10-30 20:19:09
*/
@Service
public class DiscussionCommentDomainServiceImpl extends ServiceImpl<DiscussionCommentDao, DiscussionComment>
    implements DiscussionCommentDomainService {

}




