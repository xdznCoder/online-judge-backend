package cn.xdzn.oj.service.problem.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RankDTO {
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
    @Schema(description = "总提交数")
    private Integer submit;
    @Schema(description = "个人简介")
    private String signature;
    @Schema(description = "头衔")
    private String titleName;
    @Schema(description = "头衔颜色")
    private String titleColor;
    @Schema(description = "oi总分数")
    private Integer score;
}
