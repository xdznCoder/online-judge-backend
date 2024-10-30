package cn.xdzn.oj.service.user.interfaces.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class DiscussionBackDTO {
    private Integer id;

    private Integer discussionType;

    private String title;

    private String pid;

    private String uid;

    private String author;

    private Integer viewNum;

    private Integer likeNum;

    private Integer topPriority;

    private Integer commentNum;

    private Integer status;

    private Date gmtCreate;
}
