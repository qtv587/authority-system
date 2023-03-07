package com.manong.config.oss;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="qiniu.oss.file")
public class OssProperties {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String urlPath;
}
