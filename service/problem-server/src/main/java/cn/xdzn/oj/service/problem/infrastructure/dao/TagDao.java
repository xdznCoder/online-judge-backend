package cn.xdzn.oj.service.problem.infrastructure.dao;


import cn.xdzn.oj.service.problem.domain.tag.entity.po.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【tag】的数据库操作Mapper
* @createDate 2024-10-21 10:30:16
* @Entity .domain.cn.xdzn.oj.service.problem.domain.tag.entity.po.Tag
*/
@Mapper
public interface TagDao extends BaseMapper<Tag> {

}




