package cn.xdzn.oj.service.problem.infrastructure.dao;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.ProblemTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【problem_tag】的数据库操作Mapper
* @createDate 2024-10-21 10:30:16
* @Entity .domain.cn.xdzn.oj.service.problem.domain.problem.entity.po.ProblemTag
*/
@Mapper
public interface ProblemTagDao extends BaseMapper<ProblemTag> {
    List<Integer> selectByTagIds(List<Integer> tagIds);
}




