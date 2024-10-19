package cn.xdzn.oj.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 实体基类
 * @author HeXin
 * @date 2024/03/08
 */
@Data
public class BaseEntity {

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private Date gmtModified;

    /**
     * 创建者
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @Schema(description = "创建者")
    private Long createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新者")
    private Long updateBy;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    @TableLogic(value = "0", delval = "1")
    @Schema(description = "逻辑删除(0：未删除 1：已删除)")
    private Integer isDeleted;
}
