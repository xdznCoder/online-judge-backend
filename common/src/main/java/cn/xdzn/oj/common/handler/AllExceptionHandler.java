package cn.xdzn.oj.common.handler;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.xdzn.oj.common.Result;
import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 异常处理类
 * @author HeXin
 */
@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {

    /**
     * 未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<Object> handleNotException() {
        log.error("未登录异常");
        return Result.fail().message("登录信息失效，请重新登录！").code(250);
    }

    /**
     * 处理当前用户没有权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    @ResponseBody
    public Result<Object> handleNotPermissionException() {
        log.error("处理当前用户没有权限异常");
        return Result.fail(CodeEnum.NOT_AUTHORITY);
    }

    /**
     * 处理当前用户没有角色异常
     */
    @ExceptionHandler(NotRoleException.class)
    @ResponseBody
    public Result<Object> handleNotRoleException() {
        log.error("无角色异常");
        return Result.fail().message("请登录管理员账号！").code(403);
    }

    /**
     * 服务器异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Object> deException(RuntimeException ex) {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        log.error("端口：{} 发生异常:",request.getServletPath(), ex);
        log.error("发生异常:", ex);
        return Result.fail().message(CodeEnum.SYSTEM_REPAIR.getMsg() + "\n异常信息：" + ex.getMessage()).code(CodeEnum.SYSTEM_REPAIR.getCode());
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public Result<Object> handleCustomException(CustomException ex) {
        log.error("异常信息：",ex);
        // 打印异常堆栈信息，快速定位错误位置
        ex.printStackTrace();
        return Result.fail().message(ex.getMessage()).code(ex.getCode());
    }
}
