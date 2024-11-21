package cn.xdzn.oj.service.user.domain.discussion.service.impl;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionComment;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionCommentLike;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionCommentDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.DiscussionCommentDao;
import cn.xdzn.oj.service.user.infrastructure.dao.DiscussionCommentLikeDao;
import cn.xdzn.oj.service.user.interfaces.dto.DiscussionCommentListDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Shelly6
* @description 针对表【discussion_comment】的数据库操作Service实现
* @createDate 2024-10-30 20:19:09
*/
@Service
@RequiredArgsConstructor
public class DiscussionCommentDomainServiceImpl extends ServiceImpl<DiscussionCommentDao, DiscussionComment>
    implements DiscussionCommentDomainService {
    private final DiscussionCommentLikeDao discussionCommentLikeDao;

    @Override
    public Boolean deleteCommentByDiscussionId(Long id) {
        return remove(new LambdaQueryWrapper<DiscussionComment>().eq(DiscussionComment::getDid,id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void like(Integer id,Long uid) {
        update().setSql("like_num=like_num+1").eq("id",id).update();
        discussionCommentLikeDao.insert(new DiscussionCommentLike().setCid(id).setUid(uid));
    }

    @Override
    public void deleteCommentByCid(Integer did) {
        remove(new LambdaQueryWrapper<DiscussionComment>().eq(DiscussionComment::getCid,did));
    }

    @Override
    public List<DiscussionCommentListDTO> listByDid(Long did) {
        List<DiscussionCommentListDTO> allComments = lambdaQuery()
                .select(DiscussionComment::getId, DiscussionComment::getContent,
                        DiscussionComment::getFromUid, DiscussionComment::getFromName,
                        DiscussionComment::getFromAvatar, DiscussionComment::getFromRole,
                        DiscussionComment::getGmtCreate, DiscussionComment::getCid)
                .eq(DiscussionComment::getDid, did)
                .eq(DiscussionComment::getStatus, 0)
                .eq(DiscussionComment::getIsDeleted, 0)
                .orderByAsc(DiscussionComment::getGmtCreate)
                .list()
                .stream()
                .map(
                        obj -> {
                            DiscussionCommentListDTO dto = new DiscussionCommentListDTO();
                            dto.setId(obj.getId());
                            dto.setContent(obj.getContent());
                            dto.setFromUid(obj.getFromUid());
                            dto.setFromName(obj.getFromName());
                            dto.setFromAvatar(obj.getFromAvatar());
                            dto.setFromRole(obj.getFromRole());
                            dto.setGmtCreate(obj.getGmtCreate());
                            dto.setCid(obj.getCid());
                            dto.setChildren(new ArrayList<>());
                            return dto;
                        }
                ).toList();
        // 查找最父级评论，即 cid 为 null 的评论
        List<DiscussionCommentListDTO> topComments = allComments.stream()
                .filter(comment -> comment.getCid() == null)
                .toList();
        //设置子评论
        for (DiscussionCommentListDTO topComment : topComments) {
            setChildren(topComment, allComments);
        }
        return topComments;
    }
    private void setChildren(DiscussionCommentListDTO parent, List<DiscussionCommentListDTO> allComments) {
        //获取指定父id子评论
        List<DiscussionCommentListDTO> children = allComments.stream()
                .filter(comment -> parent.getId().equals(comment.getCid()))
                .collect(Collectors.toList());

        for (DiscussionCommentListDTO child : children) {
            setChildren(child, allComments); // 递归调用设置子评论
        }

        parent.setChildren(children);
    }
}




