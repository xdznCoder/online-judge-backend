package cn.xdzn.oj.service.user.domain.announcement.service;

import cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement;
import cn.xdzn.oj.service.user.interfaces.dto.AnnouncementDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Shelly6
* @description 针对表【announcement】的数据库操作Service
* @createDate 2024-11-02 17:36:52
*/
public interface AnnouncementDomainService extends IService<Announcement> {

    IPage<AnnouncementDTO> frontPage(Long pageNum, Long pageSize, String key, Long gid);
}
