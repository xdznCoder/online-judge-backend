package cn.xdzn.oj.common.controller;

import cn.xdzn.oj.common.PageInfo;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.constants.CodeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;



/**
 * 抽象控制器
 *
 * @author HeXin
 * @date 2024/03/08
 */
public abstract class AbstractController {
    public Result<Void> isSuccess(Boolean isSuccess) {
        return Result.isSuccess(isSuccess);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public <T> Result<T> success(T data) {
        return Result.success(data);
    }

    /**
     * 成功
     *
     * @return {@link Result}<{@link T}>
     */
    public <T> Result<T> success() {
        return Result.success();
    }

    /**
     * 失败
     *
     * @return {@link Result}
     */
    public Result<Object> fail() {
        return Result.fail();
    }

    /**
     * 失败
     *
     * @param message 消息
     * @return {@link Result}<{@link ?}>
     */
    public Result<Object> fail(String message) {
        return Result.fail(message);
    }

    /**
     * 失败
     *
     * @param codeEnum 结果枚举
     * @return {@link Result}<{@link Void}>
     */
    public <T> Result<T> fail(CodeEnum codeEnum) {
        return Result.fail(codeEnum);
    }


    /**
     * 页
     *
     * @param page 页
     * @return {@link Result}<{@link PageInfo}<{@link P}>>
     */
    public <P> Result<PageInfo<P>> page(IPage<P> page) {
        return Result.page(page);
    }

    /**
     * 后果
     *
     * @param codeEnum 结果枚举
     * @return {@link Result}<{@link ?}>
     */
    public Result<Object> result(CodeEnum codeEnum) {
        return Result.result(codeEnum);
    }
}
