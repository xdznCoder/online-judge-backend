package cn.xdzn.oj.service.user.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class GroupDTO extends ConverEntity<Group> {
    private Long id;

    private String avatar;

    private String name;

    private String brief;

    private String owner;

    private Integer auth;

    private Integer visible;

    private Integer status;

    private Date gmtCreate;

}
