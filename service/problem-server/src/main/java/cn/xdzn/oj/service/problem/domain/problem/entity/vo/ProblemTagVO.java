package cn.xdzn.oj.service.problem.domain.problem.entity.vo;

import lombok.Data;

// VO 用于封装查询结果
@Data
public class ProblemTagVO {
    private Long pid; // 问题 ID
    private String tagName; // 标签名
}