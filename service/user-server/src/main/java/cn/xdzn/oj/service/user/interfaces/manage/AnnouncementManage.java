package cn.xdzn.oj.service.user.interfaces.manage;


import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.user.application.AnnouncementApplicationService;
import cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement;
import cn.xdzn.oj.service.user.domain.announcement.service.AnnouncementDomainService;
import cn.xdzn.oj.service.user.interfaces.dto.AnnouncementDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公告管理
 * @author shelly
 * @date 2024/11/2 15:03
 */
@Tag(name = "公告管理")
@RestController
@RequestMapping("/user/announcement")
@RequiredArgsConstructor
public class AnnouncementManage extends BaseController<AnnouncementDomainService, Announcement, AnnouncementDTO, Long> {

    private final AnnouncementApplicationService announcementApplicationService;
    @Override
    public Result<Void> save(AnnouncementDTO instance) {
        return super.save(instance);
    }

    @Override
    public Result<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public Result<Announcement> get(Long id) {
        return super.get(id);
    }

    @Override
    public Result<Void> update(AnnouncementDTO instance) {
        return super.update(instance);
    }

    @Operation(summary = "获取前台公告列表")
    @GetMapping("/list")
    public Result<PageInfo<AnnouncementDTO>> list(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false, defaultValue = "-1") Long gid
    ) {
        return Result.page(announcementApplicationService.frontPage(pageNum, pageSize, key, gid));
    }

    @Operation(summary = "获取后台公告列表")
    @GetMapping("/admin/list")
    public Result<PageInfo<Announcement>> adminList(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false, defaultValue = "-1") Long gid
    ) {
        return Result.page(service.lambdaQuery()
                .like(Announcement::getTitle, key)
                .eq(gid != -1, Announcement::getGid, gid)
                .page(new Page<>(pageNum, pageSize))
        );
    }
    @Operation(summary = "查看具体公告")
    @GetMapping("/detail")
    public Result<Announcement> detail(Long id) {
        return Result.success(service.getById(id));
    }

    @Override
    protected Class<Announcement> createInstance() {
        return Announcement.class;
    }
}
