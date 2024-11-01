package cn.xdzn.oj.common.interceptor;

import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.common.limit.AccessLimit;
import cn.xdzn.oj.common.util.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

import static cn.hutool.extra.servlet.JakartaServletUtil.getClientIP;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessLimitInterceptor implements HandlerInterceptor {
    private final RedisUtil redisUtil;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        boolean result = true;
        // Handler 是否为 HandlerMethod 实例
        if (handler instanceof HandlerMethod handlerMethod) {
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            //方法上没有访问控制的注解，直接通过
            if (accessLimit != null) {
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                String ip = getClientIP(request);
                String method = request.getMethod();
                String requestUri = request.getRequestURI();
                String redisKey = ip + ":" + method + ":" + requestUri;
                try {
                    Long count = redisUtil.increment(redisKey, 1L);
                    // 第一次访问
                    if (Objects.nonNull(count) && count == 1) {
                        redisUtil.expire(redisKey, seconds);
                    } else if (count > maxCount) {
                       throw new CustomException(CodeEnum.ACCESS_LIMITED);
                    }
                } catch (RedisConnectionFailureException e) {
                    log.error("redis错误: " + e.getMessage());
                    throw new CustomException(CodeEnum.SYSTEM_ERROR);
                }
            }
        }
        return result;
    }
}