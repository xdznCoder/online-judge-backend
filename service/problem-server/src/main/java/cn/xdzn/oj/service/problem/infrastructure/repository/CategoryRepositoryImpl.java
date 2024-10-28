package cn.xdzn.oj.service.problem.infrastructure.repository;

import cn.xdzn.oj.service.problem.domain.Training.entity.vo.TrainingCategoryVO;
import cn.xdzn.oj.service.problem.domain.Training.repository.CategoryRepository;
import cn.xdzn.oj.service.problem.infrastructure.dao.CategoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryDao categoryDao;
    @Override
    public List<TrainingCategoryVO> getCategory(List<Integer> cids) {
        return categoryDao.getCategoryByCid(cids);
    }
}
