package cn.xdzn.oj.service.user.domain.announcement.service.impl;


import cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement;
import cn.xdzn.oj.service.user.domain.announcement.service.AnnouncementDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.AnnouncementDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【announcement】的数据库操作Service实现
* @createDate 2024-11-02 17:36:52
*/
@Service
public class AnnouncementDomainServiceImpl extends ServiceImpl<AnnouncementDao, Announcement>
    implements AnnouncementDomainService {

}




