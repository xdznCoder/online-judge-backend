package cn.xdzn.oj.service.problem.domain.tag.entity.po;
import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName tag
 */
@TableName(value ="tag")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Tag extends BaseEntity implements Serializable {
    private Long id;

    private String name;

    private String color;

    private Long gid;

    private Date gmtCreate;

    private Date gmtModified;
    @Serial
    private static final long serialVersionUID = 1L;

}