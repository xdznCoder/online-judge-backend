package cn.xdzn.oj.service.problem.infrastructure.repository;

import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemCodeRepository;
import cn.xdzn.oj.service.problem.infrastructure.dao.ProblemDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemCodeRepositoryImpl implements ProblemCodeRepository {
    private final ProblemDao problemDao;
        public String getLastCode(Long pid, Long uid) {
        return problemDao.getLastCode(pid, uid);
    }

    @Override
    public Void addCode(Long pid, Long uid, String code, String language) {
        return problemDao.addCode(pid, uid, code, language);
    }

    @Override
    public void deleteCode(Long pid, Long uid) {
            problemDao.deleteCode(pid, uid);
    }
}
