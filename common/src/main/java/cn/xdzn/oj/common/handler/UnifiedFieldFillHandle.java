package cn.xdzn.oj.common.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 统一字段填充处理器
 *
 * @author m
 * @date 2023/6/11
 */
@Component
public class UnifiedFieldFillHandle implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        long id = 1L;
        try {
            id = StpUtil.getLoginIdAsLong();
        } catch (Exception ignored) {
            // do nothing
        }

        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        //创建人
        this.setFieldValByName("createBy", id, metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        long id = 1L;
        try {
            id = StpUtil.getLoginIdAsLong();
        } catch (Exception ignored) {
            // do nothing
        }
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        this.setFieldValByName("updateBy", id, metaObject);
    }
}
