package cn.xdzn.oj.service.user.domain.group.service;

import cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Shelly6
* @description 针对表【group_member】的数据库操作Service
* @createDate 2024-11-01 22:01:18
*/
public interface GroupMemberDomainService extends IService<GroupMember> {

    void create(Long id, String owner);

    void delete(Long id, Long userId);

    void removeByGroupId(Long id);

}
