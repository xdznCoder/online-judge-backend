package cn.xdzn.oj.service.user.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GroupMemberDTO extends ConverEntity<GroupMember> {
    private Long id;

    private Long gid;

    private String uid;

    private Integer auth;

    private String reason;

    private String username;

    private Date gmtCreate;
}
