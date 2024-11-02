package cn.xdzn.oj.service.user.domain.announcement.service.impl;


import cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement;
import cn.xdzn.oj.service.user.domain.announcement.service.AnnouncementDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.AnnouncementDao;
import cn.xdzn.oj.service.user.interfaces.assembler.AnnouncementAssembler;
import cn.xdzn.oj.service.user.interfaces.dto.AnnouncementDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public IPage<AnnouncementDTO> frontPage(Long pageNum, Long pageSize, String key, Long gid) {
        return lambdaQuery()
                .select(Announcement::getId, Announcement::getTitle,
                        Announcement::getStatus, Announcement::getGmtModified,
                        Announcement::getGid, Announcement::getUid)
                .like(key != null, Announcement::getTitle, key)
                .eq(gid != -1, Announcement::getGid, gid)
                .eq(Announcement::getStatus, 0)
                .orderByDesc(Announcement::getGmtModified)
                .page(new Page<>(pageNum, pageSize))
                .convert(AnnouncementAssembler::toDTO);
    }
}




