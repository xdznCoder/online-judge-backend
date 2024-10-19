package cn.xdzn.oj.service.user.common;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云配置
 *
 * @author HeXin
 * @date 2024/03/08
 */
@Component
public class AliYunProperties implements InitializingBean {
    /**
     * 终点
     */
    public static String END_POINT;
    /**
     * 密钥 ID
     */
    public static String KEY_ID;
    /**
     * 密钥密钥
     */
    public static String KEY_SECRET;
    /**
     * 存储桶名称
     */
    public static String BUCKET_NAME;

    /**
     * 文件夹名
     */
    public static String FOLDER_NAME;
    /**
     * 端点
     */
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    /**
     * 键 ID
     */
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    /**
     * 密钥密钥
     */
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    /**
     * 存储桶名称
     */
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    /**
     * 文件夹名
     */
    @Value("${aliyun.oss.file.foldername}")
    private String foldername;

    /**
     * 属性设置后
     *
     * @throws Exception 例外
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = this.keyid;
        KEY_SECRET = this.keysecret;
        END_POINT = this.endpoint;
        BUCKET_NAME = this.bucketname;
        FOLDER_NAME = this.foldername;
    }
}
