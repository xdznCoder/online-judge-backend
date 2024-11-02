package cn.xdzn.oj.service.user.infrastructure.dao;

import cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【announcement】的数据库操作Mapper
* @createDate 2024-11-02 17:36:52
* @Entity .cn.xdzn.oj.service.user.domain.announcement.entity.po.Announcement
*/
@Mapper
public interface AnnouncementDao extends BaseMapper<Announcement> {

}




