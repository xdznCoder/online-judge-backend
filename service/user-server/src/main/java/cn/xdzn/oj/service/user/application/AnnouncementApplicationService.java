package cn.xdzn.oj.service.user.application;

import cn.xdzn.oj.service.user.domain.announcement.service.AnnouncementDomainService;
import cn.xdzn.oj.service.user.domain.user.service.UserDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.AnnouncementDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementApplicationService {
    private final AnnouncementDomainService announcementDomainService;
    private final UserDomainService userDomainService;
    public IPage<AnnouncementDTO> frontPage(Long pageNum, Long pageSize, String key, Long gid) {
        var page = announcementDomainService.frontPage(pageNum, pageSize, key, gid);
        // 提取所有公告的 uid
        List<Long> uidList = page.getRecords().stream()
                .map(AnnouncementDTO::getUid)
                .toList();
        Map<Long, String> userNameMap = userDomainService.getUserName(uidList);
        page.getRecords().forEach(announcement -> {
            Long uid = announcement.getUid();
            String username = userNameMap.getOrDefault(uid, "神秘人");
            announcement.setName(username);
        });
        return page;
    }
}
