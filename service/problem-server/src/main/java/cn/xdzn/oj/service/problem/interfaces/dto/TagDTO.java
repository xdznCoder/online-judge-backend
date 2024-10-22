package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.problem.domain.tag.entity.po.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TagDTO extends ConverEntity<Tag> {
    @Schema(description = "主键")
    private Long id;
    @Schema(description = "标签名称")
    private String name;
    @Schema(description = "标签颜色")
    private String color;
    @Schema(description = "小组id，没有填-1")
    private Long gid;
}
