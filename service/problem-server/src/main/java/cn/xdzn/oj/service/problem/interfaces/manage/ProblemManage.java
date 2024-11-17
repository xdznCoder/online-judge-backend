package cn.xdzn.oj.service.problem.interfaces.manage;

import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.client.ProblemClient;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.problem.application.ProblemApplicationService;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.interfaces.dto.NewProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemApplyDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    @Operation(summary = "前台分页查询")
    @GetMapping("/page")
    public Result<PageInfo<ProblemFrontDTO>> page(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false) List<Integer> tagIds,
            @RequestParam(required = false, defaultValue = "-1") Integer difficulty) {
        IPage<ProblemFrontDTO> problemPage = problemApplicationService.selectPage(pageNum, pageSize, key, tagIds, difficulty);
        problemPage.setRecords(problemApplicationService.fillProblemInfo(problemPage.getRecords()));
        return Result.page(problemPage);
    }
    @Operation(summary = "获取题目详情")
    @GetMapping("/detail/{id}")
    public Result<Problem> detail(@PathVariable Long id) {
        return Result.success(service.detail(id));
    }
    @GetMapping("/random")
    @Operation(summary = "随机获取一题")
    public Result<Problem> random() {
        //不能是已经做过的题
        return Result.success(problemApplicationService.random());
    }
    @Operation(summary = "获取最新题目10题")
    @GetMapping("/newProblem")
    public Result<List<NewProblemDTO>> newProblem() {
        return Result.success(service.newProblem());
    }

//    @Operation(summary = "获取评测数据")
//    @GetMapping("/getJudgeData")
//    public Result<List<Object>> getJudgeData(Long pid) {
//        return Result.success();
//    }

    @Operation(summary = "更改加入公开题库状态")
    @PostMapping("/changePublicStatus")
    public Result<Void> changePublicStatus(Long pid, Integer status) {
        service.update().set("public_progress", status).eq("id", pid).update();
        return Result.success();
    }
    @GetMapping("/getAllTag")
    @Operation(summary = "获取所有申请")
    public Result<PageInfo<ProblemApplyDTO>> getAll(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false, defaultValue = "-1") Long gid
    ) {
        return Result.page(service.getAllApply(pageNum, pageSize, key, gid));
    }

    @GetMapping("/download")
    @Operation(summary = "导出题目zip")
    public Result<MultipartFile> exportProblem() {
        return Result.success();
    }
    @PostMapping("/upload")
    @Operation(summary = "导入题目zip")
    public Result<Void> importProblem(MultipartFile file) {
        return Result.success();
    }

//    @GetMapping("/submitList")
//    @Operation(summary = "获取题目提交列表")
//    public Result<PageInfo<Object>> codeList(
//            @RequestParam(required = false,defaultValue = "1") Long pageNum,
//            @RequestParam(required = false,defaultValue = "10")Long pageSize,
//            @RequestParam(required = false,defaultValue = "-1")Long uid,
//            @RequestParam(required = false,defaultValue = "") String key) {
//        return Result.success();
//    }
//    @GetMapping("/submitDetail")
//    @Operation(summary = "获取题目提交详情")
//    public Result<Object> codeDetail(Long sid) {
//        return Result.success();
//    }

    @GetMapping("/getLastCode")
    @Operation(summary = "获取题目最后一次代码")
    public Result<String> getProblemCode(Long pid, Long uid) {
        return Result.success(service.getLastCode(pid, uid));
    }

    @PostMapping("/updateLastCode")
    @Operation(summary = "更新题目代码")
    public Result<Void> updateLastCode(Long pid, Long uid, String code, String language) {
        return Result.success(service.updateCode(pid, uid, code,language));
    }


    @GetMapping("/addList")
    @Operation(summary = "后台添加题目列表展示")
    public Result<PageInfo<ProblemDTO>> list(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false, defaultValue = "-1") Integer auth
    ) {
        return Result.page(service.backList(pageNum, pageSize, key, auth));
    }

    @Override
    public Map<String, Long> queryMonitorInfo() {
        return problemApplicationService.queryMonitorInfo();
    }
}
