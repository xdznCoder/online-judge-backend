package cn.xdzn.oj.service.user.interfaces.assembler;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.Discussion;
import cn.xdzn.oj.service.user.interfaces.dto.DiscussionBackDTO;
import cn.xdzn.oj.service.user.interfaces.dto.DiscussionDTO;

public class DiscussionAssembler {
    private DiscussionAssembler(){}
    public static DiscussionDTO toDTO(Discussion discussion){
        return new DiscussionDTO()
                .setId(discussion.getId())
                .setDiscussionType(discussion.getDiscussionType())
                .setTitle(discussion.getTitle())
                .setDescription(discussion.getDescription())
                .setPid(discussion.getPid())
                .setUid(discussion.getUid())
                .setAuthor(discussion.getAuthor())
                .setAvatar(discussion.getAvatar())
                .setRole(discussion.getRole())
                .setViewNum(discussion.getViewNum())
                .setLikeNum(discussion.getLikeNum())
                .setTopPriority(discussion.getTopPriority())
                .setCommentNum(discussion.getCommentNum())
                .setStatus(discussion.getStatus())
                .setGid(discussion.getGid())
                .setGmtCreate(discussion.getGmtCreate());
    }
    public static DiscussionBackDTO toBackDTO(Discussion po){
        return new DiscussionBackDTO()
                .setId(po.getId())
                .setDiscussionType(po.getDiscussionType())
                .setTitle(po.getTitle())
                .setPid(po.getPid())
                .setUid(po.getUid())
                .setAuthor(po.getAuthor())
                .setViewNum(po.getViewNum())
                .setLikeNum(po.getLikeNum())
                .setTopPriority(po.getTopPriority())
                .setCommentNum(po.getCommentNum())
                .setStatus(po.getStatus())
                .setGmtCreate(po.getGmtCreate());

    }
}
