package cn.xdzn.oj.service.system.domain.system.entity.po;

import cn.xdzn.oj.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @TableName system_config
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="system_config")
@Data
@Accessors(chain = true)
public class SystemConfig extends BaseEntity implements Serializable {
    private Long id;

    private String baseUrl;

    private String siteName;

    private String siteShortName;

    private String recordName;

    private String recordAddress;

    private String projectName;

    private String projectAddress;

    private String siteDescription;

    private Integer allowRegistration;

    private String host;

    private Integer port;

    private String email;

    private String emailPassword;

    private String emailBackground;

    private Integer sslEnabled;

    private Integer evaluationInterval;
    @Serial
    private static final long serialVersionUID = 1L;
}