package cn.xdzn.oj.service.problem.domain.problem.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemCodeRepository;
import cn.xdzn.oj.service.problem.domain.problem.repository.ProblemTagRepository;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.ProblemDao;
import cn.xdzn.oj.service.problem.interfaces.assembler.ProblemAssembler;
import cn.xdzn.oj.service.problem.interfaces.dto.NewProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemApplyDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProblemDomainServiceImpl extends ServiceImpl<ProblemDao, Problem> implements ProblemDomainService {

    private final ProblemCodeRepository problemCodeRepository;

    @Override
    public List<ProblemFrontDTO> fill(List<ProblemFrontDTO> records, Map<Long, Integer> submitNumMap, Map<Long, Integer> acNumMap, Map<Long, List<String>> tagNamesMap,List<Long> acList) {
        for (ProblemFrontDTO dto : records) {
            Long problemId = dto.getId();
            dto.setSubmitNum(submitNumMap.get(problemId));
            dto.setAcNum(acNumMap.get(problemId));
            dto.setAcRate(dto.getAcNum() * 100.0 / dto.getSubmitNum());
            dto.setTagNames(tagNamesMap.get(problemId));
            dto.setIsAc(acList.contains(problemId));
        }
        return records;
    }

    @Override
    public Problem detail(Long id) {
        return lambdaQuery()
                .select(Problem::getId, Problem::getTitle,
                        Problem::getDescription,Problem::getProblemId,
                        Problem::getInput, Problem::getOutput, Problem::getExamples,
                        Problem::getDifficulty, Problem::getHint, Problem::getTimeLimit,
                        Problem::getMemoryLimit, Problem::getStackLimit,
                        Problem::getType, Problem::getGid,Problem::getCodeShare,Problem::getJudgeMode,
                        Problem::getFunctionCode, Problem::getFunctionLanguage, Problem::getIsRemoveEndBlank,
                        Problem::getIoScore, Problem::getCreateBy, Problem::getAuthor,Problem::getIsUploadCase)
                .eq(Problem::getId, id)
                .eq(Problem::getIsDeleted, 0)
                .oneOpt()
                .orElseThrow(() -> new CustomException("题目不存在"));
    }

    @Override
    public List<NewProblemDTO> newProblem() {
        return   lambdaQuery()
                .select(Problem::getId, Problem::getTitle, Problem::getGmtCreate, Problem::getDifficulty, Problem::getProblemId)
                .eq(Problem::getIsDeleted, 0)
                .eq(Problem::getAuth, 1)
                .orderByDesc(Problem::getGmtCreate)
                .last("limit 10")
                .list()
                .stream()
                .filter(problem -> problem.getApplyPublicProgress() == null || problem.getApplyPublicProgress() == 2)
                .map(ProblemAssembler::toNewProblemDTO)
                .toList();
    }

    @Override
    public String getLastCode(Long pid, Long uid) {
        return problemCodeRepository.getLastCode(pid, uid);
    }

    @Override
    public Void updateCode(Long pid, Long uid, String code, String language) {
        problemCodeRepository.deleteCode(pid, uid);
        return problemCodeRepository.addCode(pid, uid, code, language);
    }

    @Override
    public IPage<ProblemApplyDTO> getAllApply(Long pageNum, Long pageSize, String key, Long gid) {
        return lambdaQuery()
                .select(Problem::getId, Problem::getTitle, Problem::getApplyPublicProgress
                , Problem::getGid, Problem::getProblemId, Problem::getType,Problem::getAuthor,Problem::getCreateBy)
                .like(StringUtils.isNotBlank(key), Problem::getTitle, key)
                .eq(gid != null, Problem::getGid, gid)
                .eq(Problem::getIsDeleted, 0)
                .page(new Page<>(pageNum, pageSize))
                .convert(ProblemApplyDTO::toDTO);
    }

    @Override
    public IPage<ProblemDTO> backList(Long pageNum, Long pageSize, String key, Integer auth) {
        return lambdaQuery()
                .select(Problem::getId, Problem::getTitle,
                        Problem::getGmtCreate, Problem::getDifficulty,
                        Problem::getProblemId, Problem::getAuth,
                        Problem::getCreateBy, Problem::getModifiedUser, Problem::getAuthor
                , Problem::getGid,Problem::getGmtCreate)
                .like(StringUtils.isNotBlank(key), Problem::getTitle, key)
                .eq(auth != -1, Problem::getAuth, auth)
                .eq(Problem::getIsDeleted, 0)
                .page(new Page<>(pageNum, pageSize))
                .convert(ProblemAssembler::toDTO);
    }
}
