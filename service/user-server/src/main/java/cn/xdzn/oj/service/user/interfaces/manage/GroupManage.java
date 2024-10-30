package cn.xdzn.oj.service.user.interfaces.manage;


import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.user.domain.group.entity.po.Group;
import cn.xdzn.oj.service.user.domain.group.service.GroupDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.GroupDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 团队管理
 * @author shelly
 * @date 2024-10-28
 */
@Tag(name = "团队管理")
@RestController
@RequestMapping("/user/group")
public class GroupManage extends BaseController<GroupDomainService, Group, GroupDTO, Long> {
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

    @Override
    protected Class<Group> createInstance() {
        return Group.class;
    }
}
