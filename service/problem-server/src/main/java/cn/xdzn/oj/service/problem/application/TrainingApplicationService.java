package cn.xdzn.oj.service.problem.application;

import cn.dev33.satoken.stp.StpUtil;
import cn.xdzn.oj.common.client.UserClient;
import cn.xdzn.oj.common.constants.CodeEnum;
import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.problem.domain.training.entity.po.Training;
import cn.xdzn.oj.service.problem.domain.training.entity.po.TrainingRegister;
import cn.xdzn.oj.service.problem.domain.training.entity.vo.TrainingCategoryVO;
import cn.xdzn.oj.service.problem.domain.training.entity.vo.TrainingNumVO;
import cn.xdzn.oj.service.problem.domain.training.repository.CategoryRepository;
import cn.xdzn.oj.service.problem.domain.training.repository.TrainingProblemRepository;
import cn.xdzn.oj.service.problem.domain.training.service.TrainingDomainService;
import cn.xdzn.oj.service.problem.domain.training.service.TrainingRegisterDomainService;
import cn.xdzn.oj.service.problem.domain.problem.service.ProblemDomainService;
import cn.xdzn.oj.service.problem.interfaces.dto.ProblemFrontDTO;
import cn.xdzn.oj.service.problem.interfaces.dto.TrainingFrontDTO;
import cn.xdzn.oj.service.problem.domain.problem.entity.po.Problem;
import cn.xdzn.oj.service.problem.interfaces.dto.TrainingPasswordDTO;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 训练服务
 * @author shelly
 */
@Service
@RequiredArgsConstructor
public class TrainingApplicationService {
    private final TrainingDomainService trainingDomainService;
    private final UserClient userClient;
    private final TrainingProblemRepository trainingProblemRepository;
    private final CategoryRepository categoryRepository;
    private final TrainingRegisterDomainService trainingRegisterDomainService;
    private final ProblemDomainService problemDomainService;



    // 在 Application 层
    public List<TrainingFrontDTO> fillTrainingInfo(List<TrainingFrontDTO> records) {
        List<Long> ids = records.stream().map(TrainingFrontDTO::getId).toList();
        List<Integer> cIds = records.stream().map(TrainingFrontDTO::getCid).toList();

        Map<Long, Integer> problemNumMap = trainingProblemRepository.getProblemNum(ids)
                .stream().collect(Collectors.toMap(TrainingNumVO::getId, TrainingNumVO::getProblemNum));

        Map<Integer, TrainingCategoryVO> categoryMap = categoryRepository.getCategory(cIds)
                .stream().collect(Collectors.toMap(TrainingCategoryVO::getCid, vo -> vo));

        Map<Integer, Integer> acNums = userClient.getTrainingAcNum(ids);

        return trainingDomainService.fill(records, problemNumMap, categoryMap, acNums);
    }


    public void verifyPassword(TrainingPasswordDTO dto) {
        String password = trainingDomainService.lambdaQuery().select(Training::getPrivatePwd).eq(Training::getId,dto.getId()).eq(Training::getIsDeleted,0).one().getPrivatePwd();
        if(StringUtils.isNotBlank(password) && password.equals(dto.getTrainingPassword())){
            trainingRegisterDomainService.save(
                    new TrainingRegister()
                            .setUid(StpUtil.getLoginIdAsLong())
                            .setStatus(1)
                            .setTid(dto.getId()));
        }
        else{
            throw new CustomException(CodeEnum.PASSWORD_ERROR);
        }
    }

    public IPage<ProblemFrontDTO> problemList(Long id, Long pageNum, Long pageSize) {
        Integer auth = trainingDomainService.lambdaQuery()
                .select(Training::getAuth)
                .eq(Training::getId, id).one().getAuth();
        if(auth != null && auth == 1){
           Long cnt = trainingRegisterDomainService.lambdaQuery()
                    .select(TrainingRegister::getId)
                    .eq(TrainingRegister::getTid, id)
                    .eq(TrainingRegister::getUid, StpUtil.getLoginIdAsLong())
                    .eq(TrainingRegister::getIsDeleted, 0)
                    .count();
           if(cnt == 0){
               throw new CustomException(CodeEnum.NOT_AUTHORITY);
           }
        }
        //题目ids
        List<Integer> list = trainingProblemRepository.getProblemIds(id);
        return problemDomainService.lambdaQuery()
                .select(Problem::getId, Problem::getTitle, Problem::getDifficulty, Problem::getProblemId)
                .in(Problem::getId, list)
                .page(new Page<>(pageNum, pageSize))
                .convert(ProblemFrontDTO::toDTO);
    }
}
