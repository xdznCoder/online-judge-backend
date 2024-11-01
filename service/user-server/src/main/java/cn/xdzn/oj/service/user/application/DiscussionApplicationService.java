package cn.xdzn.oj.service.user.application;

import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionComment;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionCommentDomainService;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.DiscussionCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class DiscussionApplicationService {
    private final DiscussionDomainService discussionDomainService;
    private final DiscussionCommentDomainService discussionCommentDomainService;

    public void like(Long id, int type) {
        switch (type) {
            case 1 -> discussionDomainService.like(id);
            case 2 -> discussionCommentDomainService.like(id);
            default -> throw new CustomException("未知类型: " + type);
        }
    }

    public void addComment(DiscussionCommentDTO comment) {
        DiscussionComment po = comment.toPo(DiscussionComment.class);
        po.verifyComment(po);
        Integer did = comment.getDid();
        Integer cid = comment.getCid();
        //验证是否存在该帖子
        if(discussionDomainService.getById(did) == null){
            throw new CustomException("帖子不存在");
        }
        if(cid != null && discussionCommentDomainService.getById(cid) == null){
            throw new CustomException("评论不存在");
        }
        discussionCommentDomainService.save(po);
    }
}
