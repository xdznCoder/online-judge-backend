package cn.xdzn.oj.service.problem.domain.problem.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@TableName(value ="problem")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Problem extends BaseEntity implements Serializable {

    private Long id;

    private String problemId;

    private String title;

    private String author;

    private Integer type;

    private Integer timeLimit;

    private Integer memoryLimit;

    private Integer stackLimit;

    private String description;

    private String input;

    private String output;

    private String examples;

    private Integer difficulty;

    private String hint;

    private Integer auth;

    private Integer ioScore;

    private Integer codeShare;

    private String judgeMode;

    private String functionCode;

    private String functionLanguage;

    private Integer isRemoveEndBlank;

    private Integer openCaseResult;

    private Integer isUploadCase;

    private String caseVersion;

    private String modifiedUser;

    private Integer isGroup;

    private Long gid;

    private Integer applyPublicProgress;

    @Serial
    private static final long serialVersionUID = 1L;
}