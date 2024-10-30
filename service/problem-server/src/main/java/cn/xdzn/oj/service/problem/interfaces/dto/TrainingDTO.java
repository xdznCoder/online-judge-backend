package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.problem.domain.training.entity.po.Training;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TrainingDTO extends ConverEntity<Training> {
    private Long id;

    private String title;

    private String author;

    private Integer auth;
    @Schema(description = "排序")
    private Integer rank;
    @Schema(description = "分类id")
    private Integer cid;

    private String description;

    private String privatePwd;
    @Schema(description = "训练状态(0表示不可用，1表示可用)")
    private Integer status;
    //TODO 后面有空删除
    @Schema(description = "是否为团队(1表示yes，0表示no)")
    private Integer isGroup;

    @Schema(description = "团队id，没有为-1")
    private Long gid;


}
