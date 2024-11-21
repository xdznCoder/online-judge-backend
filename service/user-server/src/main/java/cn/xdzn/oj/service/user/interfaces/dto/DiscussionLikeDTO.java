package cn.xdzn.oj.service.user.interfaces.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DiscussionLikeDTO {
    private Long id;
    private List<Integer> commentLikes;
    private Boolean isDiscussionLike;
}
