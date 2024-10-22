package cn.xdzn.oj.service.problem.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WeekAcRank {
    @Schema(description = "主键")
    private Long id;
    @Schema(description = "用户id")
    private Long uid;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "通过题目数")
    private Integer ac;
}
