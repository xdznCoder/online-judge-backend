package cn.xdzn.oj.service.problem.domain.Training.service.impl;

import cn.xdzn.oj.service.problem.domain.Training.entity.po.Category;
import cn.xdzn.oj.service.problem.domain.Training.service.CategoryDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.CategoryDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryDomainServiceImpl extends ServiceImpl<CategoryDao, Category>
        implements CategoryDomainService {
}
