package cn.xdzn.oj.service.user.domain.group.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@TableName(value ="group")
@Data
@Accessors(chain = true)
public class Group extends BaseEntity implements Serializable {
    private Long id;

    private String avatar;

    private String name;

    private String shortName;

    private String brief;

    private String description;

    private String owner;

    private Integer auth;

    private Integer visible;

    private Integer status;

    private String code;

    @Serial
    private static final long serialVersionUID = 1L;
}