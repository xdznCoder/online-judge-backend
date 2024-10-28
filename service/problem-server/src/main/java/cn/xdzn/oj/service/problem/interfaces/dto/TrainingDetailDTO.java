package cn.xdzn.oj.service.problem.interfaces.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain  = true)
public class TrainingDetailDTO {
    private String description;

    @Schema(description = "用户id")
    private Long id;

    private Boolean isAuth;

}
