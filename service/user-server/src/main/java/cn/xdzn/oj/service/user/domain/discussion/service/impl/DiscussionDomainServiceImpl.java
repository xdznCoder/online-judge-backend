package cn.xdzn.oj.service.user.domain.discussion.service.impl;

import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.service.user.domain.discussion.entity.po.Discussion;
import cn.xdzn.oj.service.user.domain.discussion.service.DiscussionDomainService;
import cn.xdzn.oj.service.user.infrastructure.dao.DiscussionDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author Shelly6
* @description 针对表【discussion】的数据库操作Service实现
* @createDate 2024-10-28 19:57:39
*/
@Service
public class DiscussionDomainServiceImpl extends ServiceImpl<DiscussionDao, Discussion>
    implements DiscussionDomainService{

    @Override
    public void checkAndUpdate(Long id) {
        Long count = lambdaQuery().eq(Discussion::getId, id).count();
        if (count <= 0) {
            throw new CustomException("该讨论不存在");
        }
        update().setSql("view_num = view_num + 1").eq("id", id).update();
    }
}




