package cn.xdzn.oj.service.user.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GroupJoinParamDTO {

    private Long gid;

    private String uid;

    private String reason;

    private String code;
    @Schema(description = "团队类型，0：public 1:protected 2:private")
    private Integer auth;
}
