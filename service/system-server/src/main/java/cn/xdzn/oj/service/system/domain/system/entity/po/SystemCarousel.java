package cn.xdzn.oj.service.system.domain.system.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @TableName system_carousel
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="system_carousel")
@Data
@Accessors(chain = true)
public class SystemCarousel extends BaseEntity implements Serializable {
    private Integer id;

    private String url;
    @Serial
    private static final long serialVersionUID = 1L;
}