package cn.xdzn.oj.service.problem.infrastructure.dao;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目映射器
 *
 * @author shelly
 * @description 针对表【problem】的数据库操作Mapper
 * @createDate 2024-10-20 17:08:14
 * @date 2024/10/20
 */
@Mapper
public interface ProblemDao extends BaseMapper<Problem> {
    String getLastCode(Long pid, Long uid);

    Void addCode(Long pid, Long uid, String code, String language);

    void deleteCode(Long pid, Long uid);
}
