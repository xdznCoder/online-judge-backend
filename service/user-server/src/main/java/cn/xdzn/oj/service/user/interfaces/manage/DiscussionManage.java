package cn.xdzn.oj.service.user.interfaces.manage;

import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.common.limit.AccessLimit;
import cn.xdzn.oj.service.user.application.DiscussionApplicationService;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.Discussion;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionLike;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionReport;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionCommentDomainService;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionDomainService;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionReportDomainService;
import cn.xdzn.oj.service.user.interfaces.assembler.DiscussionAssembler;
import cn.xdzn.oj.service.user.interfaces.dto.*;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 讨论管理
 * @author shelly
 * @date 2024-10-28
 */
@Tag(name = "讨论管理")
@RestController
@RequestMapping("/user/discussion")
@RequiredArgsConstructor
//finish
public class DiscussionManage extends BaseController<DiscussionDomainService, Discussion, DiscussionDTO, Long>{

    private final DiscussionReportDomainService reportService;
    private final DiscussionCommentDomainService commentService;
    private final DiscussionApplicationService applicationService;
    @Override
    public Result<Void> save(DiscussionDTO instance) {
        return super.save(instance);
    }

    @Override
    public Result<Void> delete(Long id) {
        service.removeById(id);
        commentService.deleteCommentByDiscussionId(id);
        return super.delete(id);
    }

    @Override
    public Result<Discussion> get(Long id) {
        return super.get(id);
    }

    @Override
    public Result<Void> update(DiscussionDTO instance) {
        return super.update(instance);
    }
    @GetMapping("/frontPage")
    @Operation(summary = "前台分页查询")
    public Result<PageInfo<DiscussionDTO>> frontPage(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false,defaultValue = "-1") Long gid,
            @RequestParam(required = false) Long uid,
            @RequestParam(required = false, defaultValue = "-1")Long discussionType
    ) {
        return Result.page(service.lambdaQuery()
                .select(Discussion::getId, Discussion::getTitle, Discussion::getDiscussionType,
                        Discussion::getPid, Discussion::getUid, Discussion::getAuthor,
                        Discussion::getAvatar, Discussion::getRole,
                        Discussion::getViewNum, Discussion::getLikeNum,
                        Discussion::getTopPriority, Discussion::getCommentNum,
                        Discussion::getStatus, Discussion::getGid,
                        Discussion::getGmtCreate, Discussion::getDescription
                        )
                .like(StringUtils.isNotBlank(key), Discussion::getTitle, key)
                .like(StringUtils.isNotBlank(key), Discussion::getDescription, key)
                .eq(uid != null, Discussion::getUid, uid)
                .eq(gid != -1, Discussion::getGid, gid)
                .eq(Discussion::getStatus,1)
                .eq(discussionType != -1, Discussion::getDiscussionType, discussionType)
                .eq(Discussion::getIsDeleted,0)
                .orderByDesc(Discussion::getTopPriority)
                .orderByDesc(Discussion::getGmtModified)
                .page(new Page<>(pageNum, pageSize))
                .convert(DiscussionAssembler::toDTO));
    }
    @GetMapping("/backPage")
    @Operation(summary = "后台分页查询")
    public Result<PageInfo<DiscussionBackDTO>> backPage(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false, defaultValue = "-1")Long discussionType
    ) {
        return Result.page(service.lambdaQuery()
                .select(Discussion::getId, Discussion::getTitle, Discussion::getDiscussionType,
                        Discussion::getPid, Discussion::getUid, Discussion::getAuthor,
                        Discussion::getViewNum, Discussion::getLikeNum,
                        Discussion::getTopPriority, Discussion::getCommentNum,
                        Discussion::getStatus, Discussion::getGmtCreate
                        )
                .like(StringUtils.isNotBlank(key), Discussion::getTitle, key)
                .eq(discussionType != -1, Discussion::getDiscussionType, discussionType)
                .eq(Discussion::getIsDeleted,0)
                .page(new Page<>(pageNum, pageSize))
                .convert(DiscussionAssembler::toBackDTO)
                );

    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "获取讨论详情")
    public Result<Discussion> detail(@PathVariable Long id) {
        service.checkAndUpdate(id);
        return Result.success(service.getById(id));
    }
    @PostMapping("/createDiscussion")
    @Operation(summary = "创建讨论")
    public Result<Void> create(@RequestBody Discussion po) {
        return Result.isSuccess(service.save(po));
    }
    @PostMapping("/updateDiscussion")
    @Operation(summary = "编辑讨论")
    public Result<Void> update(@RequestBody Discussion po) {
        Long count = service.lambdaQuery().eq(Discussion::getId, po.getId()).count();
        if (count == 0) {
            return Result.fail("该讨论不存在");
        }
        return Result.isSuccess(service.updateById(po));
    }
    @DeleteMapping("/deleteDiscussion/{id}")
    @Operation(summary = "删除讨论")
    public Result<Void> deleteDiscussion(@PathVariable Long id) {
        Long count = service.lambdaQuery().eq(Discussion::getId, id).count();
        if (count == 0) {
            return Result.fail("该讨论不存在");
        }
        Boolean isSuccess = service.removeById(id);
        Boolean isSuccess2 = commentService.deleteCommentByDiscussionId(id);
        return Result.isSuccess(isSuccess && isSuccess2);
    }
    @PostMapping("/like")
    @Operation(summary = "点赞")
    @AccessLimit(seconds = 10, maxCount = 3)
    public Result<Void> like(@RequestBody Integer id,@RequestParam(required = false, defaultValue = "1")@Schema(description = "1为讨论点赞，2为评论点赞") int type) {
        applicationService.like(id,type);
         //TODO: 通知
        return Result.success();
    }

    @PostMapping("/report")
    @Operation(summary = "举报")
    public Result<Void> report(@RequestBody DiscussionReportDTO report) {
        return Result.isSuccess(reportService.save(report.toPo(DiscussionReport.class)));
    }

    @PostMapping("/dealReport/{id}/{status}")
    @Operation(summary = "处理举报")
    public Result<Void> dealReport(@PathVariable Long id,@PathVariable Integer status) {
        DiscussionReport report = reportService.getById(id);
        if (report == null) {
            return Result.fail("该举报不存在");
        }
        report.setStatus(status);
        reportService.updateById(report);
        return Result.success();
    }

    @GetMapping("/reportList")
    @Operation(summary = "举报列表")
    public Result<PageInfo<DiscussionReport>> reportList(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize
    ) {
        return Result.page(reportService.lambdaQuery()
                .page(new Page<>(pageNum, pageSize))
        );
    }
    @PostMapping("/comment")
    @Operation(summary=  "回复评论")
    public Result<Void> comment(@RequestBody DiscussionCommentDTO comment) {
        applicationService.addComment(comment);
        //TODO: 通知
        return Result.success();
    }
    @DeleteMapping("/comment/{id}/{did}")
    @Operation(summary = "删除评论")
    public Result<Void> deleteComment(@PathVariable Long id, @PathVariable Integer did) {
        commentService.removeById(id);
        commentService.deleteCommentByCid(did);
        return Result.success();
    }
    @GetMapping("/commentList")
    @Operation(summary = "获取评论列表")
    public Result<List<DiscussionCommentListDTO>> commentList(@RequestParam Long did) {
        return Result.success(commentService.listByDid(did));
    }
    @Operation(summary = "查询个人点赞列表", description = "只会查询到id,数据回显,前端动态绑定")
    @GetMapping("/userLikes/{id}")
    public Result<DiscussionLikeDTO> selectLikesByUid(@PathVariable Long id) {
        return Result.success(applicationService.selectLikesByUid(id));
    }
    @Override
    protected Class<Discussion> createInstance() {
        return Discussion.class;
    }
}
