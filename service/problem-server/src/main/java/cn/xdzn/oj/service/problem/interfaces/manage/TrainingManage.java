package cn.xdzn.oj.service.problem.interfaces.manage;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.problem.application.ProblemApplicationService;
import cn.xdzn.oj.service.problem.application.TrainingApplicationService;
import cn.xdzn.oj.service.problem.domain.training.entity.po.Category;
import cn.xdzn.oj.service.problem.domain.training.entity.po.Training;
import cn.xdzn.oj.service.problem.domain.training.entity.po.TrainingRegister;
import cn.xdzn.oj.service.problem.domain.training.service.CategoryDomainService;
import cn.xdzn.oj.service.problem.domain.training.service.TrainingDomainService;
import cn.xdzn.oj.service.problem.domain.training.service.TrainingRegisterDomainService;
import cn.xdzn.oj.service.problem.interfaces.assembler.CategoryAssembler;
import cn.xdzn.oj.service.problem.interfaces.assembler.TrainingAssembler;
import cn.xdzn.oj.service.problem.interfaces.dto.*;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 训练管理
 * @author shelly
 */
@RestController
@RequiredArgsConstructor
@io.swagger.v3.oas.annotations.tags.Tag(name = "训练模块")
@RequestMapping("/problem/training")
public class TrainingManage extends BaseController<TrainingDomainService, Training, TrainingDTO, Long> {

    private final CategoryDomainService categoryDomainService;

    private final TrainingApplicationService trainingApplicationService;

    private final TrainingRegisterDomainService trainingRegisterDomainService;

    private final ProblemApplicationService problemApplicationService;

    @Override
    //创建就写入author
    public Result<Void> save(TrainingDTO instance) {
        return super.save(instance);
    }

    @Override
    public Result<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public Result<Training> get(Long id) {
        return super.get(id);
    }

    @Override
    public Result<Void> update(TrainingDTO instance) {
        return super.update(instance);
    }

    @GetMapping("/page")
    @Operation(summary = "前台分页查询")
    public Result<PageInfo<TrainingFrontDTO>> page(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false,defaultValue = "-1") int authId,
            @RequestParam(required = false,defaultValue = "-1") int groupId,
            @RequestParam(required = false,defaultValue = "-1") Long categoryId
    ) {
       var page = service.lambdaQuery()
               .select(Training::getId, Training::getTitle,Training::getGid, Training::getAuth, Training::getRank
                       ,Training::getGmtModified, Training::getAuthor, Training::getCid)
               .like(StringUtils.isNotBlank(key), Training::getTitle, key)
               .eq(authId != -1, Training::getAuth, authId)
               .eq(groupId != -1, Training::getGid, groupId)
               .eq(Training::getStatus,1)
               .eq(categoryId != -1, Training::getCid, categoryId)
               .orderByAsc(Training::getRank)
               .page(new Page<>(pageNum, pageSize))
               .convert(TrainingAssembler::toFrontDTO);
       page.setRecords(trainingApplicationService.fillTrainingInfo(page.getRecords()));
       return Result.page(page);
    }
    @GetMapping("/backPage")
    @Operation(summary = "后台分页查询")
    public Result<PageInfo<Training>> backPage(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false, defaultValue = "-1") Integer groupId,
            @RequestParam(required = false,defaultValue = "-1") Long categoryId
    ){
        var page = service.lambdaQuery()
                .select(Training::getId, Training::getTitle, Training::getGid, Training::getAuth, Training::getRank
                        ,Training::getStatus, Training::getGmtModified
                       ,Training::getGmtModified, Training::getAuthor, Training::getCid)
               .like(StringUtils.isNotBlank(key), Training::getTitle, key)
               .eq(groupId != -1, Training::getGid, groupId)
               .eq(categoryId != -1, Training::getCid, categoryId)
               .eq(Training::getIsDeleted,0)
               .orderByAsc(Training::getRank)
               .page(new Page<>(pageNum, pageSize));
        return Result.page(page);
    }

    @PostMapping("/addProblemList")
    @Operation(summary = "添加题目时列表展示")
    public Result<PageInfo<ProblemSimpleDTO>> addProblemList(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key
    ){
        return Result.page(problemApplicationService.searchList(pageNum, pageSize, key));
    }

    @GetMapping("/category")
    @Operation(summary = "查询训练分类")
    //分类设置上限10个
    //考虑做成聚合
    public Result<List<CategoryDTO>> category(
            @RequestParam(required = false,defaultValue = "-1") int groupId
    ) {
        return Result.success(categoryDomainService.lambdaQuery()
                .select(Category::getId, Category::getName, Category::getColor, Category::getGid)
                .eq(groupId != -1, Category::getGid, groupId)
                .eq(Category::getIsDeleted, 0)
                .list()
                .stream()
                .map(CategoryAssembler::toDTO)
                .toList());
    }

    @PostMapping("/addCategory")
    @Operation(summary = "新增训练分类")
    public Result<Void> addCategory(@RequestBody CategoryDTO instance) {
        categoryDomainService.save(instance.toPo(Category.class));
        return Result.success();
    }
    @DeleteMapping("/deleteCategory/{id}")
    @Operation(summary = "删除训练分类")
    public Result<Void> deleteCategory(@PathVariable Long id) {
       service.deleteCategory(id);
        return Result.success();
    }
    @GetMapping("/detail/{id}/{auth}")
    @Operation(summary = "查询训练详情(主要判断权限以及返回描述信息)")
    public Result<TrainingDetailDTO> detail(@PathVariable Long id, @PathVariable Integer auth) {
        String description = service.lambdaQuery().select(Training::getDescription).eq(Training::getId, id).eq(Training::getIsDeleted,0).one().getDescription();
        TrainingDetailDTO trainingDetailDTO = new TrainingDetailDTO().setId(id).setDescription(description);
        if(auth != null && auth == 1){
            Long count = trainingRegisterDomainService
                    .lambdaQuery()
                    .eq(TrainingRegister::getTid, id)
                    .eq(TrainingRegister::getUid, StpUtil.getLoginId())
                    .eq(TrainingRegister::getIsDeleted, 0)
                    .count();
            trainingDetailDTO.setIsAuth(count > 0);
        }
        return Result.success(trainingDetailDTO);
    }

    @PostMapping("/verifyPassword")
    @Operation(summary = "验证训练密码")
    public Result<Void> verifyPassword(@RequestBody TrainingPasswordDTO dto) {
        trainingApplicationService.verifyPassword(dto);
        return Result.success();
    }

    @GetMapping("/problemList")
    @Operation(summary = "查询训练题目列表")
    public Result<PageInfo<ProblemFrontDTO>> problemList(Long id,
    @RequestParam(required = false, defaultValue = "1") Long pageNum,
    @RequestParam(required = false, defaultValue = "10") Long pageSize) {
        IPage<ProblemFrontDTO> problemPage = trainingApplicationService.problemList(id, pageNum, pageSize);
        problemPage.setRecords(problemApplicationService.fillProblemInfo(problemPage.getRecords()));
        return Result.page(problemPage);
    }
    @PostMapping("/addProblem")
    @Operation(summary = "添加题目")
    public Result<Void> addProblem(Long tid, Long pid,String displayId) {
        service.addProblem(tid, pid, displayId);
        return Result.success();
    }
    @DeleteMapping("/deleteProblem/{id}")
    @Operation(summary = "移除题目")
    public Result<Void> deleteProblem(@PathVariable Long id) {
        service.deleteProblem(id);
        return Result.success();
    }

    @Override
    protected Class<Training> createInstance() {
        return Training.class;
    }
}
