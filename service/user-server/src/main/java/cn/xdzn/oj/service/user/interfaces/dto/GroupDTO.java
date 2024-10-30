package cn.xdzn.oj.service.user.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupDTO extends ConverEntity<Group> {
}
