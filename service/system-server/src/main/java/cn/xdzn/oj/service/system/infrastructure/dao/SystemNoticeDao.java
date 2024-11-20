package cn.xdzn.oj.service.system.infrastructure.dao;

import cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【system_notice】的数据库操作Mapper
* @createDate 2024-11-20 17:44:25
* @Entity .cn.xdzn.oj.service.system.domain.notice.entity.po.SystemNotice
*/
@Mapper
public interface SystemNoticeDao extends BaseMapper<SystemNotice> {

}




