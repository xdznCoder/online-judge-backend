package cn.xdzn.oj.service.problem.infrastructure.dao;

import cn.xdzn.oj.service.problem.domain.Training.entity.po.Category;
import cn.xdzn.oj.service.problem.domain.Training.entity.vo.TrainingCategoryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao extends BaseMapper<Category> {

    List<TrainingCategoryVO> getCategoryByCid(List<Integer> cids);
}

