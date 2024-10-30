package cn.xdzn.oj.service.problem.domain.training.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName category
 */
@TableName(value ="category")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity implements Serializable {
    private Long id;

    private String name;

    private String color;

    private Long gid;
    @Serial
    private static final long serialVersionUID = 1L;
}