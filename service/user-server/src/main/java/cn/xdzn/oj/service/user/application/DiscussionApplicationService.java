package cn.xdzn.oj.service.user.application;

import cn.xdzn.oj.common.client.ProblemClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class DiscussionApplicationService {
    private ProblemClient problemClient;

}
