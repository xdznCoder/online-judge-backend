package cn.xdzn.oj.service.problem.interfaces.manage;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.problem.application.service.ProblemApplicationService;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.infrastructure.client.ProblemClient;
import cn.xdzn.oj.service.problem.interfaces.dto.NewProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 题目管理
 * @author shelly
 * @data 2024/10/20 15:06
 */
@Tag(name = "题目模块")
@RestController
@RequestMapping("/problem")
@RequiredArgsConstructor
public class ProblemManage extends BaseController<ProblemDomainService, Problem, ProblemDTO, Long> implements ProblemClient {

    private final ProblemApplicationService problemApplicationService;

    @Override
    protected Class<Problem> createInstance() {
        return Problem.class;
    }


    @Override
    public Result<Void> save(ProblemDTO instance) {
        return super.save(instance);
    }

    @Override
    public Result<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public Result<Problem> get(Long id) {
        return super.get(id);
    }

    @Override
    public Result<Void> update(ProblemDTO instance) {
        return super.update(instance);
    }

    @Operation(summary = "分页查询")
    @SaCheckPermission("")
    @GetMapping("/page")
    public Result<PageInfo<Problem>> page(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) List<Integer> tagIds,
            @RequestParam(required = false, defaultValue = "-1") Integer type) {
        return Result.page(problemApplicationService.selectPage(pageNum, pageSize, key, tagIds, type));
    }
    @Operation(summary = "获取最新题目10题")
    @GetMapping("/newProblem")
    public Result<NewProblemDTO> newProblem() {
        return Result.success();
    }

    @Operation(summary = "获取评测数据")
    @GetMapping("/getJudgeData")
    public Result<List<Object>> getJudgeData(Long pid) {
        return Result.success();
    }

    @Operation(summary = "更改加入公开题库状态")
    @PostMapping("/changePublicStatus")
    public Result<Void> changePublicStatus(Long pid, Integer status) {
        return Result.success();
    }

    @GetMapping("/download")
    @Operation(summary = "导出题目zip")
    public Result<MultipartFile> exportProblem(Long pid) {
        return Result.success();
    }
    @PostMapping("/upload")
    @Operation(summary = "导入题目zip")
    public Result<Void> importProblem(MultipartFile file) {
        return Result.success();
    }





}
