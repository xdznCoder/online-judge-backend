package cn.xdzn.oj.service.user.application;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.Discussion;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionComment;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionCommentLike;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionLike;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionCommentDomainService;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.DiscussionCommentDao;
import cn.xdzn.oj.service.user.infrastructure.dao.DiscussionCommentLikeDao;
import cn.xdzn.oj.service.user.infrastructure.dao.DiscussionLikeDao;
import cn.xdzn.oj.service.user.interfaces.dto.DiscussionCommentDTO;
import cn.xdzn.oj.service.user.interfaces.dto.DiscussionLikeDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DiscussionApplicationService {
    private final DiscussionDomainService discussionDomainService;
    private final DiscussionCommentDomainService discussionCommentDomainService;
    private final DiscussionCommentLikeDao discussionCommentLikeDao;
    private final DiscussionLikeDao discussionLikeDao;

    public void like(Integer id, int type) {
        Long uid = StpUtil.getLoginIdAsLong();
        switch (type) {
            //讨论
            case 1 -> discussionDomainService.like(id,uid);
            //评论
            case 2 -> discussionCommentDomainService.like(id,uid);
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

    public DiscussionLikeDTO selectLikesByUid(Long id) {
        Long uid = StpUtil.getLoginIdAsLong();
        Discussion discussion = discussionDomainService.getById(id);
        if (discussion == null) {
            throw new CustomException("帖子不存在");
        }
        //查询出帖子是否点赞
        Boolean isDiscussionLike = discussionLikeDao.exists(new LambdaQueryWrapper<>(DiscussionLike.class)
                .eq(DiscussionLike::getUid, uid)
                .eq(DiscussionLike::getDid, id)
                .eq(DiscussionLike::getIsDeleted, 0)
        );

        DiscussionLikeDTO po = new DiscussionLikeDTO()
                .setId(id)
                .setIsDiscussionLike(isDiscussionLike);

        //查询出帖子下的所有评论id
        List<Integer> commentIds = discussionCommentDomainService.lambdaQuery()
                .select(DiscussionComment::getCid)
                .eq(DiscussionComment::getDid, id)
                .eq(DiscussionComment::getIsDeleted, 0)
                .list()
                .stream()
                .map(DiscussionComment::getId)
                .toList();
        if (commentIds.isEmpty()) {
            return po;
        }
        //查询出这些评论子下的所有点赞id
        // 查询这些评论下的所有点赞 id
        List<Integer> commentLikeIds = discussionCommentLikeDao.selectList(
                        new LambdaQueryWrapper<DiscussionCommentLike>()
                                .eq(DiscussionCommentLike::getUid, uid)
                                .in(DiscussionCommentLike::getCid, commentIds)
                                .eq(DiscussionCommentLike::getIsDeleted, 0)
                )
                .stream()
                .map(DiscussionCommentLike::getCid)
                .toList();
        po.setCommentLikes(commentLikeIds);
        return po;
    }
}
