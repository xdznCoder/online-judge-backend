package cn.xdzn.oj.service.problem.infrastructure.dao;

import cn.xdzn.oj.service.problem.domain.problem.entity.po.ProblemTag;
import cn.xdzn.oj.service.problem.domain.problem.entity.vo.ProblemTagVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【problem_tag】的数据库操作Mapper
* @createDate 2024-10-21 10:30:16
* @Entity .domain.cn.xdzn.oj.service.problem.domain.problem.entity.po.ProblemTag
*/
@Mapper
public interface ProblemTagDao extends BaseMapper<ProblemTag> {

    @Select("SELECT pt.pid AS pid, t.name AS tagName " +
            "FROM problem_tag pt " +
            "JOIN tag t ON pt.tid = t.id " +
            "WHERE pt.pid IN #{problemIds} " +
            "AND pt.is_deleted = 0 " +
            "AND t.is_deleted = 0")
    List<ProblemTagVO> findTagsByProblemIds(@Param("problemIds") List<Long> problemIds);
}




