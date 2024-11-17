package cn.xdzn.oj.common.util;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Minio配置
 * @author shelly
 */
@Component
@Data
public class MinioProperties implements InitializingBean {

    @Value("${minio.url}")
    private String instanceUrl;

    @Value("${minio.access-key}")
    private String instanceAccessKey;

    @Value("${minio.secret-key}")
    private String instanceSecretKey;

    @Value("${minio.bucket-name}")
    private String instanceBucketName;

    private String url;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    @Override
    public void afterPropertiesSet() {
        // 用注入的实例配置更新实例字段
        this.url = instanceUrl;
        this.accessKey = instanceAccessKey;
        this.secretKey = instanceSecretKey;
        this.bucketName = instanceBucketName;
    }
}

