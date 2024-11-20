package cn.xdzn.oj.service.system.infrastructure.dao;
import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNoticeUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【system_notice_user】的数据库操作Mapper
* @createDate 2024-11-20 17:54:09
* @Entity .SystemNoticeUser
*/
@Mapper
public interface SystemNoticeUserDao extends BaseMapper<SystemNoticeUser> {

}




