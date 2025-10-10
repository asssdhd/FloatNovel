package org.example.floatnovel.controller;

import org.example.floatnovel.service.QiniuService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class uploadController {

    private final QiniuService qiniuService;

    public uploadController(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = qiniuService.uploadFile(file.getInputStream(), file.getOriginalFilename());
            return "上传成功：" + url;
        } catch (Exception e) {
            return "上传失败：" + e.getMessage();
        }
    }
}
