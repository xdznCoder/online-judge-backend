package cn.xdzn.oj.common.service;

import cn.xdzn.oj.common.exception.CustomException;
import cn.xdzn.oj.common.util.MinioProperties;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


/**
 * 文件上传服务
 * @author shelly
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    // 上传图片到 Minio
    public String uploadPicture2Minio(@RequestParam("file") MultipartFile file) throws IOException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InsufficientDataException, ErrorResponseException, InvalidResponseException, XmlParserException, InternalException {
        // 限制文件大小为3MB
        if (file.getSize() > 3145728L) {
            throw new CustomException("图片大小最大为3M！", 280);
        }

        // 检查存储桶是否存在，不存在则创建
        String bucketName = minioProperties.getBucketName();
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }

        // 获取文件名和文件扩展名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 判断图片格式
            if (!".jpg".equals(suffix) && !".png".equals(suffix) && !".jpeg".equals(suffix)) {
                throw new CustomException("图片格式错误！");
            }

            // 上传文件到 Minio
            String url = minioProperties.getUrl();
            return uploadFileToMinio(file, originalFilename, bucketName, url);
        }

        throw new CustomException("上传失败，文件名不能为空！");
    }

    // 上传文件到 Minio
    private String uploadFileToMinio(MultipartFile file, String originalFileName, String bucketName, String url){
        String key = UUID.randomUUID().toString().replace("-", ""); // 随机文件名
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String filename = String.format("%s%s", key, suffix);
        log.info("==> 开始上传文件至 Minio, ObjectName: {}", filename);

        try {
            // 上传文件到 Minio
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            );
            String returnUrl = url + "/" + bucketName + "/" + filename;
            log.info("==> 上传文件至 Minio 成功，访问路径: {}", returnUrl);
            return returnUrl;
        } catch (Exception e) {
            throw new CustomException("文件上传失败：" + e.getMessage());
        }
    }
}
