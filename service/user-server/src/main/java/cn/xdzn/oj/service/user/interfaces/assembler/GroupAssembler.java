package cn.xdzn.oj.service.user.interfaces.assembler;

import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember;
import cn.xdzn.oj.service.user.interfaces.dto.GroupDTO;
import cn.xdzn.oj.service.user.interfaces.dto.GroupMemberDTO;

public class GroupAssembler {
    private GroupAssembler(){}

    public static GroupDTO toDTO(Group group) {
        return new GroupDTO()
                .setId(group.getId())
                .setAvatar(group.getAvatar())
                .setName(group.getName())
                .setBrief(group.getBrief())
                .setOwner(group.getOwner())
                .setAuth(group.getAuth())
                .setVisible(group.getVisible())
                .setStatus(group.getStatus())
                .setGmtCreate(group.getGmtCreate());
    }
    public static GroupMemberDTO toDTO(GroupMember member) {
        return new GroupMemberDTO()
                .setId(member.getId())
                .setGid(member.getGid())
                .setUid(member.getUid())
                .setAuth(member.getAuth())
                .setReason(member.getReason())
                .setGmtCreate(member.getGmtCreate());
    }
}
