package cn.xdzn.oj.service.user.application;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.user.domain.group.service.GroupDomainService;
import cn.xdzn.oj.service.user.domain.group.service.GroupMemberDomainService;
import cn.xdzn.oj.service.user.domain.user.service.UserDomainService;
import cn.xdzn.oj.service.user.interfaces.assembler.GroupAssembler;
import cn.xdzn.oj.service.user.interfaces.dto.GroupDTO;
import cn.xdzn.oj.service.user.interfaces.dto.GroupJoinParamDTO;
import cn.xdzn.oj.service.user.interfaces.dto.GroupMemberDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember;
import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupApplicationService {

    private final GroupMemberDomainService groupMemberDomainService;

    private final GroupDomainService groupDomainService;
    private final UserDomainService userDomainService;
    public IPage<GroupDTO> frontPage(Long pageNum, Long pageSize, String key, Long userId, Integer auth) {
        // 获取用户所加入的组,包括隐藏的组
        List<Long> ids = new ArrayList<>(groupMemberDomainService
                .lambdaQuery()
                .select(GroupMember::getGid)
                .eq(GroupMember::getUid, userId)
                .eq(GroupMember::getIsDeleted, 0)
                .in(GroupMember::getAuth, Arrays.asList(3, 4, 5))
                .list()
                .stream().map(GroupMember::getGid).toList());
        // 获取用户未加入的组，包括非隐藏的组
        ids.addAll(groupDomainService
                .lambdaQuery()
                .select(Group::getId)
                .eq(Group::getIsDeleted, 0)
                .eq(Group::getVisible, 1)
                .eq(Group::getStatus,0)
                .list()
                .stream().map(Group::getId).toList());
        // 去重
        List<Long> uniqueIds = new ArrayList<>(new HashSet<>(ids));
        if(CollUtil.isEmpty(ids)){
            return new Page<>(pageNum, pageSize);
        }
        return groupDomainService.lambdaQuery()
                .select(Group::getId, Group::getName, Group::getAvatar,
                        Group::getBrief, Group::getOwner, Group::getAuth,
                        Group::getVisible, Group::getStatus, Group::getGmtCreate)
                .like(key != null, Group::getName, key)
                .in(Group::getId, uniqueIds)
                .eq(auth != null, Group::getAuth, auth)
                .eq(Group::getIsDeleted, 0)
                .eq(Group::getStatus, 0)
                .page(new Page<>(pageNum, pageSize))
                .convert(GroupAssembler::toDTO);
    }

    public void create(Group group) {
        //查询团队名称是否重复
        groupDomainService.existName(group.getName());
        //创建团队
        groupDomainService.save(group);
        //增加团队成员
        groupMemberDomainService.create(group.getId(), group.getOwner());
    }

    public void quit(Long id) {
        Long userId = StpUtil.getLoginIdAsLong();
        Long count = groupDomainService.lambdaQuery().eq(Group::getId, id).eq(Group::getOwner, userId).count();
        if(count > 0){
            throw new CustomException("创建者不能退出团队");
        }
        groupMemberDomainService.delete(id, userId);
    }

    public void dismiss(Long id) {
        Long userId = StpUtil.getLoginIdAsLong();
        Integer auth = groupMemberDomainService
                .lambdaQuery().eq(GroupMember::getGid, id)
                .eq(GroupMember::getUid, userId).one().getAuth();
        if(auth == null || auth != 5){
            throw new CustomException("权限不足");
        }
        groupDomainService.removeById(id);
        groupMemberDomainService.removeByGroupId(id);
    }

    public void join(GroupJoinParamDTO groupJoinParamDTO) {
        if(groupJoinParamDTO.getAuth() != null && groupJoinParamDTO.getAuth() == 2){
            //verify
            Group one = groupDomainService.lambdaQuery()
                    .select(Group::getCode)
                    .eq(Group::getId, groupJoinParamDTO.getGid())
                    .eq(Group::getIsDeleted, 0)
                    .eq(Group::getStatus, 0)
                    .one();
            if(one == null){
                throw new CustomException(CodeEnum.DATA_NOT_EXIST);
            }
            if(!groupJoinParamDTO.getCode().equals(one.getCode())){
                throw new CustomException(CodeEnum.CODE_ERROR);
            }
            groupMemberDomainService.save(new GroupMember()
                    .setGid(groupJoinParamDTO.getGid())
                    .setUid(String.valueOf(StpUtil.getLoginIdAsLong()))
                    .setAuth(groupJoinParamDTO.getAuth())
                    .setReason(groupJoinParamDTO.getReason())
                    .setAuth(1)
            );
        }
    }

    public IPage<GroupMemberDTO> members(Long id, Long pageNum, Long pageSize,int type) {
        //提取用户id
        List<Long> userIds  = groupMemberDomainService.lambdaQuery()
                .select(GroupMember::getUid)
                .eq(GroupMember::getGid, id)
                .eq(GroupMember::getIsDeleted, 0)
                .in(type == -1, GroupMember::getAuth, Arrays.asList(3, 4, 5))
                .list()
                .stream()
                .map(groupMember -> Long.valueOf(groupMember.getUid()))
                .toList();
        Map<Long,String> userNameMap = userDomainService.getUserName(userIds);

        IPage<GroupMemberDTO> convert = groupMemberDomainService.lambdaQuery()
                .select(GroupMember::getId, GroupMember::getGid, GroupMember::getUid,
                        GroupMember::getAuth, GroupMember::getGmtCreate)
                .select(type == 1 ,GroupMember::getReason )
                .eq(GroupMember::getGid, id)
                .eq(GroupMember::getIsDeleted, 0)
                .in(type == -1,GroupMember::getAuth, Arrays.asList(3, 4, 5))
                .page(new Page<>(pageNum, pageSize))
                .convert(GroupAssembler::toDTO);
        convert.getRecords().forEach(groupMemberDTO -> groupMemberDTO.setUsername(userNameMap.get(Long.valueOf(groupMemberDTO.getUid()))));
        return convert;
    }


}
