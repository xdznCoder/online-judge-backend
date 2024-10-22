package cn.xdzn.oj.service.problem.interfaces.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewProblemDTO {
    private Long id;

    private String problemId;

    private String title;

    private Date gmtCreate;

    private Integer difficulty;
}
