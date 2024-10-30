package cn.xdzn.oj.service.user.infrastructure.dao;


import cn.xdzn.oj.service.user.domain.discussion.entity.po.DiscussionReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【discussion_report】的数据库操作Mapper
* @createDate 2024-10-30 20:19:09
* @Entity .DiscussionReport
*/
@Mapper
public interface DiscussionReportDao extends BaseMapper<DiscussionReport> {

}




