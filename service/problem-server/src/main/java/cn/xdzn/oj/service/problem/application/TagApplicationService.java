package cn.xdzn.oj.service.problem.application;

import cn.xdzn.oj.service.problem.domain.tag.service.TagDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
/**
 * 标签应用层
 * @author shelly
 * @date 2024/10/21 10:03
 */
@Service
@RequiredArgsConstructor
public class TagApplicationService {
    private final TagDomainService tagDomainService;
}
