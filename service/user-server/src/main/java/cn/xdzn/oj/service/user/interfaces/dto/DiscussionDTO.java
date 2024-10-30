package cn.xdzn.oj.service.user.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.Discussion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class DiscussionDTO extends ConverEntity<Discussion> {
    private Integer id;

    private Integer discussionType;

    private String title;

    private String description;

    private String pid;

    private String uid;

    private String author;

    private String avatar;

    private String role;

    private Integer viewNum;

    private Integer likeNum;

    private Integer topPriority;

    private Integer commentNum;

    private Integer status;

    private Long gid;

    private Date gmtCreate;

}
