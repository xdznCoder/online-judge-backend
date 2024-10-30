package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.user.entity.po.UserAcproblem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author Shelly6
* @description 针对表【user_acproblem】的数据库操作Mapper
* @createDate 2024-10-26 17:20:09
* @Entity .UserAcproblem
*/
@Mapper
public interface UserAcproblemDao extends BaseMapper<UserAcproblem> {
    @MapKey("key")
    Map<Integer, Integer> getAcNum(Long userId, List<Long> tidList);

    List<Long> getUserAc(Long uid);
    @MapKey("key")
    Map<Long, Integer> getProblemAcNum(List<Long> ids);
}