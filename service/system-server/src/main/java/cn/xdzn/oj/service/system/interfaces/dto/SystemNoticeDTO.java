package cn.xdzn.oj.service.system.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNotice;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemNoticeDTO extends ConverEntity<SystemNotice> {
}
