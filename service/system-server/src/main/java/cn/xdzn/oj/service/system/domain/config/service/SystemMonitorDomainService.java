package cn.xdzn.oj.service.system.domain.config.service;

import cn.xdzn.oj.service.system.interfaces.dto.SystemMonitorServerDTO;

public interface SystemMonitorDomainService {
    /**
     * 获取服务器监控信息
     */
    SystemMonitorServerDTO serverInfo();

}
