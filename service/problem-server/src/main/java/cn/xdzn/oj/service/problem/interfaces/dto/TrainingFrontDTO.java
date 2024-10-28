package cn.xdzn.oj.service.problem.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.problem.domain.Training.entity.po.Training;
import cn.xdzn.oj.service.problem.domain.Training.entity.vo.TrainingCategoryVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class TrainingFrontDTO extends ConverEntity<Training> {
    private Long id;

    private String title;

    private String author;

    private Integer auth;

    private Integer rank;

    private String category;

    private Integer cid;

    private String color;

    private Date gmtModified;

    private Integer problemNum;

    private Integer ac;

    private Double acRate;

    private Long createBy;

    public static void fill(TrainingFrontDTO training, Integer problemNum, Integer ac, TrainingCategoryVO category){
        training.setAc(ac)
                .setProblemNum(problemNum)
                .setCategory(category.getCategory())
                .setColor(category.getColor());
    }
}