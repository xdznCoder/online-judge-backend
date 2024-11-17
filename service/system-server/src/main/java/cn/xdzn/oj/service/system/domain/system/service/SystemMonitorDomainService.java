package cn.xdzn.oj.service.system.domain.system.service;

import cn.xdzn.oj.service.system.interfaces.dto.SystemMonitorServerDTO;

public interface SystemMonitorDomainService {
    /**
     * 获取服务器监控信息
     */
    SystemMonitorServerDTO serverInfo();

}
