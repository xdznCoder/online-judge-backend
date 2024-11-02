package cn.xdzn.oj.service.user.interfaces.manage;


import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.user.application.GroupApplicationService;
import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import cn.xdzn.oj.service.user.domain.group.entity.po.GroupMember;
import cn.xdzn.oj.service.user.domain.group.service.GroupDomainService;
import cn.xdzn.oj.service.user.domain.group.service.GroupMemberDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.GroupDTO;
import cn.xdzn.oj.service.user.interfaces.dto.GroupJoinParamDTO;
import cn.xdzn.oj.service.user.interfaces.dto.GroupMemberDTO;
import com.alibaba.cloud.commons.lang.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 团队管理
 * @author shelly
 * @date 2024-10-28
 */
@Tag(name = "团队管理")
@RestController
@RequestMapping("/user/group")
@RequiredArgsConstructor
public class GroupManage extends BaseController<GroupDomainService, Group, GroupDTO, Long> {

    private final GroupApplicationService groupApplicationService;
    private final GroupMemberDomainService groupMemberDomainService;

    @Override
    public Result<Void> save(GroupDTO instance) {
        return super.save(instance);
    }

    @Override
    public Result<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public Result<Group> get(Long id) {
        return super.get(id);
    }

    @Override
    public Result<Void> update(GroupDTO instance) {
        return super.update(instance);
    }

    @GetMapping("/frontPage")
    @Operation(summary = "前台分页")
    public Result<PageInfo<GroupDTO>> frontPage(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer auth
    ) {
        return Result.page(groupApplicationService.frontPage(pageNum, pageSize, key, userId, auth));
    }
    @PostMapping("/create")
    @Operation(summary = "创建团队")
    public Result<Void> create(@RequestBody Group group) {
        groupApplicationService.create(group);
        return Result.success();
    }


    @GetMapping("/join")
    @Operation(summary = "加入团队")
    public Result<Void> join(@RequestBody GroupJoinParamDTO groupJoinParamDTO) {
        groupApplicationService.join(groupJoinParamDTO);
        return Result.success();
    }
    @GetMapping("/quit/{id}")
    @Operation(summary = "退出团队")
    public Result<Void> quit(@PathVariable Long id) {
        //创始人不能退出
        groupApplicationService.quit(id);
        return Result.success();
    }
    @PutMapping("/updateGroup")
    @Operation(summary = "修改团队信息")
    public Result<Void> updateGroup(@RequestBody Group group) {
        return Result.isSuccess(service.updateById(group));
    }
    @DeleteMapping("/dismiss/{id}")
    @Operation(summary = "解散团队")
    public Result<Void> dismiss(@PathVariable Long id) {
        groupApplicationService.dismiss(id);
        return Result.success();
    }

    @GetMapping("/members")
    @Operation(summary = "团队成员")
    public Result<PageInfo<GroupMemberDTO>> members(
            @RequestParam Long id,
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false, defaultValue = "-1")@Schema(description = "-1为成员，1为成员管理列表") int type
    ) {
        return Result.page(groupApplicationService.members(id,pageNum, pageSize,type));
    }

    @PutMapping("/member}")
    @Operation(summary = "修改团队成员")
    public Result<Void> member(@RequestBody GroupMember groupMember) {
        return Result.isSuccess(groupMemberDomainService.updateById(groupMember));
    }

    @Override
    protected Class<Group> createInstance() {
        return Group.class;
    }
}
