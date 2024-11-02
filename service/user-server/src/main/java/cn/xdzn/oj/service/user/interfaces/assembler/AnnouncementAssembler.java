package cn.xdzn.oj.service.user.interfaces.assembler;

import cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement;
import cn.xdzn.oj.service.user.interfaces.dto.AnnouncementDTO;

public class AnnouncementAssembler {
    private AnnouncementAssembler(){}

    public static AnnouncementDTO toDTO(Announcement po){
        return new AnnouncementDTO()
                .setId(po.getId())
                .setTitle(po.getTitle())
                .setUid(po.getUid())
                .setStatus(po.getStatus())
                .setGmtModified(po.getGmtModified())
                .setGid(po.getGid());
    }
}
