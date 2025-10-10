package org.example.floatnovel.service;


import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class QiniuService {

    private final UploadManager uploadManager;
    private final Auth auth;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.domain}")
    private String domain;

    public QiniuService(UploadManager uploadManager, Auth auth) {
        this.uploadManager = uploadManager;
        this.auth = auth;
    }

    public String uploadFile(InputStream inputStream, String originalFilename) {
        try {
            String key = "uploads/" + UUID.randomUUID() + "-" + originalFilename;
            String upToken = auth.uploadToken(bucket);
            uploadManager.put(inputStream, key, upToken, null, null);
            return domain + "/" + key;
        } catch (Exception e) {
            throw new RuntimeException("上传七牛云失败", e);
        }
    }
}
