package cn.xdzn.oj.service.problem.domain.training.repository;

import cn.xdzn.oj.service.problem.domain.training.entity.vo.TrainingCategoryVO;

import java.util.List;
public interface CategoryRepository {
    List<TrainingCategoryVO> getCategory(List<Integer> cids);
}
