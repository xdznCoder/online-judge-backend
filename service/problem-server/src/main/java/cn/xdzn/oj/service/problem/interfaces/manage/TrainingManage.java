package cn.xdzn.oj.service.problem.interfaces.manage;

import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.controller.BaseController;
import cn.xdzn.oj.service.problem.domain.Training.entity.po.Training;
import cn.xdzn.oj.service.problem.domain.Training.service.TrainingDomainService;
import cn.xdzn.oj.service.problem.interfaces.dto.CategoryDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.TrainingDTO;
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

    @Override
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
    @Operation(summary = "分页查询")
    //别忘了返回进度
    public Result<PageInfo<TrainingDTO>> page(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            @RequestParam(required = false) String key,
            @RequestParam(required = false,defaultValue = "-1") int authId,
            @RequestParam(required = false,defaultValue = "-1") Long categoryId
    ) {
        return Result.success();
    }

    @GetMapping("/category")
    @Operation(summary = "查询训练分类")
    //分类设置上限10个
    //考虑做成聚合
    public Result<List<TrainingDTO>> category(
            @RequestParam(required = false,defaultValue = "-1") int groupId
    ) {
        return Result.success();
    }

    @PostMapping("/addCategory")
    @Operation(summary = "新增训练分类")
    public Result<Void> addCategory(@RequestBody CategoryDTO instance) {
        return Result.success();
    }
    @GetMapping("/detail")
    @Operation(summary = "查询训练详情")
    public Result<TrainingDTO> detail(Long id) {
        return Result.success();
    }
    @GetMapping("/problemList")
    @Operation(summary = "查询训练题目列表")
    public Result<PageInfo<ProblemDTO>> problemList(Long id,
    @RequestParam(required = false, defaultValue = "1") Long pageNum,
    @RequestParam(required = false, defaultValue = "10") Long pageSize) {
        return Result.success();
    }

    @Override
    protected Class<Training> createInstance() {
        return Training.class;
    }
}
