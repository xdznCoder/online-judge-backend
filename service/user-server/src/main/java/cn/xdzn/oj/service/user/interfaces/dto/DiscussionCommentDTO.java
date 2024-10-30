package cn.xdzn.oj.service.user.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DiscussionCommentDTO extends ConverEntity<DiscussionComment> {
}
