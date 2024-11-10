package cn.xdzn.oj.service.problem.interfaces.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class NewProblemDTO {
    private Long id;

    private String problemId;

    private String title;

    private Date gmtCreate;

    private Integer difficulty;
}
