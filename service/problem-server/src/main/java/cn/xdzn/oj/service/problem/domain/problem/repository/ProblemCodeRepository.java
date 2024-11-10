package cn.xdzn.oj.service.problem.domain.problem.repository;
/**
 * 仓储接口
 */
public interface ProblemCodeRepository {
    public String getLastCode(Long pid, Long uid);

    Void addCode(Long pid, Long uid, String code, String language);

    void deleteCode(Long pid, Long uid);
}
