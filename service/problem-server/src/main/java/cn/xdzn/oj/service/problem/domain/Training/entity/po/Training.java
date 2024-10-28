package cn.xdzn.oj.service.problem.domain.Training.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName training
 */
@TableName(value ="training")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
//new
public class Training extends BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String author;

    private Integer auth;

    private String privatePwd;

    private Integer rank;

    private Integer cid;

    private Integer status;

    private Integer isGroup;

    private Long gid;

    @Serial
    private static final long serialVersionUID = 1L;

}