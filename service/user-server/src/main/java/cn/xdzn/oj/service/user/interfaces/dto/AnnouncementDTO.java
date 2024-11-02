package cn.xdzn.oj.service.user.interfaces.dto;

import cn.xdzn.oj.common.entity.ConverEntity;
import cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AnnouncementDTO extends ConverEntity<Announcement> {
    private Long id;

    private String title;

    private Long uid;

    private Integer status;

    private Long gid;

    private Date gmtModified;

    private String name;
}
