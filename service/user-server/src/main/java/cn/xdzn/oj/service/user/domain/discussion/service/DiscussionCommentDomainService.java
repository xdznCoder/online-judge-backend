package cn.xdzn.oj.service.user.domain.discussion.service;

import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionComment;
import cn.xdzn.oj.service.user.interfaces.dto.DiscussionCommentListDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Shelly6
* @description 针对表【discussion_comment】的数据库操作Service
* @createDate 2024-10-30 20:19:09
*/
public interface DiscussionCommentDomainService extends IService<DiscussionComment> {

    Boolean deleteCommentByDiscussionId(Long id);

    void like(Long id);


    void deleteCommentByCid(Integer did);

    List<DiscussionCommentListDTO> listByDid(Long did);
}
