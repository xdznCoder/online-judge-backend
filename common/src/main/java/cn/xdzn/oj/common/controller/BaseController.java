package cn.xdzn.oj.common.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.entity.BaseEntity;
import cn.xdzn.oj.common.entity.ConverEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 基本控制器
 *
 * @author HeXin
 * @date 2024/03/08
 */
@SaCheckLogin
public abstract class
BaseController<S extends IService<E>, E extends BaseEntity,T extends ConverEntity<E>, K extends Serializable>
        extends AbstractController {
    /**
     * 服务
     */
    @Getter
    @Autowired
    protected S service;

    private final Class<E> entity;

    protected BaseController(){
        // 通过子类传递泛型类型参数
        entity = createInstance();
    }
    protected abstract Class<E> createInstance();


    /**
     * 保存
     *
     * @param instance 实例
     * @return {@link Result}<{@link Void}>
     */
    @SaCheckPermission("admin.add")
    @Operation(summary = "新增",description = "id不需要填写，直接删除id那行")
    @PostMapping()
    public Result<Void> save(@RequestBody T instance) {
        service.save(instance.toPo(entity));
        return success();
    }

    /**
     * 删除
     *
     * @param id id
     * @return {@link Result}<{@link Void}>
     */
    @SaCheckPermission("admin.delete")
    @Operation(summary = "根据id删除")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@Parameter(description = "主键") @PathVariable K id) {
        service.removeById(id);
        return success();
    }

    /**
     * 获取
     *
     * @param id id
     * @return {@link Result}<{@link E}>
     */
    @SaCheckPermission("admin.get")
    @Operation(summary = "根据id查询")
    @GetMapping("/{id}")
    public Result<E> get(@Parameter(description = "主键") @PathVariable K id) {
        return success(service.getById(id));
    }

    /**
     * 更新
     *
     * @param instance 实例
     * @return {@link Result}<{@link ?}>
     */
    @SaCheckPermission("admin.update")
    @Operation(summary = "更新")
    @PutMapping
    public Result<Void> update(@RequestBody T instance) {
        return isSuccess(service.updateById(instance.toPo(entity)));
    }
}
