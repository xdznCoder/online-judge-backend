package cn.xdzn.oj.service.user.interfaces.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DiscussionCommentListDTO {
    private Integer id;

    private Integer cid;

    private String content;

    private String fromUid;

    private String fromName;

    private String fromAvatar;

    private String fromRole;

    private Date gmtCreate;

    private List<DiscussionCommentListDTO> children;

}
