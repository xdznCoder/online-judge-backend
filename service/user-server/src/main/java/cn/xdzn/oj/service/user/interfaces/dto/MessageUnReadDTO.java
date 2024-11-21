package cn.xdzn.oj.service.user.interfaces.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MessageUnReadDTO {
    private Integer commentUnRead;
    private Integer replyUnRead;
    private Integer likeUnRead;
    private Integer noticeUnRead;
    private Integer otherUnRead;
}
