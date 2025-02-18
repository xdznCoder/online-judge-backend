package cn.xdzn.oj.service.problem.domain.tag.entity.po;
import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName tag
 */
@TableName(value ="tag")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
//new
public class Tag extends BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "id")
    private Long id;

    private String name;

    private String color;

    @Schema(description = "团队id")
    private Long gid;

    @Serial
    private static final long serialVersionUID = 1L;
}