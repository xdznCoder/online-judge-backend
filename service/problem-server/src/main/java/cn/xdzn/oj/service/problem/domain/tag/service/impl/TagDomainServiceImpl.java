package cn.xdzn.oj.service.problem.domain.tag.service.impl;

import cn.xdzn.oj.service.problem.domain.tag.entity.po.Tag;
import cn.xdzn.oj.service.problem.domain.tag.service.TagDomainService;
import cn.xdzn.oj.service.problem.infrastructure.dao.TagDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TagDomainServiceImpl extends ServiceImpl<TagDao, Tag> implements TagDomainService {
}
