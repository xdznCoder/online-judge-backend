package cn.xdzn.oj.service.problem.domain.Training.repository;

import cn.xdzn.oj.service.problem.domain.Training.entity.vo.TrainingCategoryVO;

import java.util.List;
public interface CategoryRepository {
    List<TrainingCategoryVO> getCategory(List<Integer> cids);
}
