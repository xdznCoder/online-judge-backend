package cn.xdzn.oj.service.user.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionReport;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DiscussionReportDTO extends ConverEntity<DiscussionReport> {
    private Integer id;

    private Integer did;

    private String reporter;

    private String content;

    private Integer status;
}
