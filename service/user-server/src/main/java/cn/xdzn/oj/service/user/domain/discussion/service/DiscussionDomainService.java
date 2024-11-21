package cn.xdzn.oj.service.user.domain.discussion.service;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.Discussion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Shelly6
* @description 针对表【discussion】的数据库操作Service
* @createDate 2024-10-28 19:57:39
*/
public interface DiscussionDomainService extends IService<Discussion> {

    void checkAndUpdate(Long id);

    void like(Integer id, Long uid);
}