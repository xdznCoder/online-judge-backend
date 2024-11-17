package cn.xdzn.oj.service.user.application;

import cn.xdzn.oj.service.user.domain.user.entity.po.UserAcproblem;
import cn.xdzn.oj.service.user.domain.user.repository.UserAcProblemRepository;
import cn.xdzn.oj.service.user.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserApplicationService {
    private final UserDomainService userDomainService;
    private final UserAcProblemRepository userAcProblemRepository;
    public Map<String, Long> queryMonitorInfo() {
        Map <String, Long> map = new HashMap<>();
        //user count
        Long count = userDomainService.count();
        map.put("userCount", count);
        //problem ac
        Long ac = userAcProblemRepository.getTotalAcNum();
        map.put("passCount", ac);
        return map;
    }
}

