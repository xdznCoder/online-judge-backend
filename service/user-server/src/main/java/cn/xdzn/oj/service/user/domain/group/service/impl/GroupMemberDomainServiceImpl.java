package cn.xdzn.oj.service.user.domain.group.service.impl;


import cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember;
import cn.xdzn.oj.service.user.domain.group.service.GroupMemberDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.GroupMemberDao;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【group_member】的数据库操作Service实现
* @createDate 2024-11-01 22:01:18
*/
@Service
public class GroupMemberDomainServiceImpl extends ServiceImpl<GroupMemberDao, GroupMember>
    implements GroupMemberDomainService {

    @Override
    public void create(Long id, String owner) {
        GroupMember groupMember = new GroupMember();
        groupMember.setGid(id);
        groupMember.setUid(owner);
        groupMember.setAuth(5);
        save(groupMember);
    }

    @Override
    public void delete(Long id, Long userId) {
        remove(new LambdaQueryWrapper<GroupMember>()
                .eq(GroupMember::getGid, id)
                .eq(GroupMember::getUid, userId));
    }

    @Override
    public void removeByGroupId(Long id) {
        remove(new LambdaQueryWrapper<GroupMember>()
                .eq(GroupMember::getGid, id));
    }


}




