package org.example.floatnovel.config;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
/*第二阶段
七牛云配置
2025.10.8 */
@org.springframework.context.annotation.Configuration
public class QiniuConfig {

    @Value("${qiniu.access-key}")
    private String accessKey;

    @Value("${qiniu.secret-key}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Bean
    public Auth qiniuAuth() {
        return Auth.create(accessKey, secretKey);
    }

    @Bean
    public UploadManager uploadManager() {
        // 这里根据存储区域选择对应 Region，如 Region.huadong()
        return new UploadManager(new Configuration(Region.autoRegion()));
    }
}
