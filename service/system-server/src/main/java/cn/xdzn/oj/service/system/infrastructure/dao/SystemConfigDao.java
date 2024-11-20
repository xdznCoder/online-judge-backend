package cn.xdzn.oj.service.system.infrastructure.dao;


import cn.xdzn.oj.service.system.domain.config.entity.po.SystemConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Shelly6
* @description 针对表【system_config(系统配置表)】的数据库操作Mapper
* @createDate 2024-11-16 14:31:47
* @Entity .SystemConfig
*/
@Mapper
public interface SystemConfigDao extends BaseMapper<SystemConfig> {

}




