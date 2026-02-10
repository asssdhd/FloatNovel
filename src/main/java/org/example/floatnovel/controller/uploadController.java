package org.example.floatnovel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/upload")
@Tag(name = "上传模块")
public class uploadController {

    @Autowired
    private FileStorageService fileStorageService;//注入实列

    @PostMapping
    @Operation(summary = "文件上传接口")
    public String upload(@RequestParam("file") MultipartFile file) {

        String objectName = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))+ "/";

        FileInfo fileInfo = fileStorageService.of(file)
                .setPath(objectName) //保存到相对路径下，为了方便管理，不需要可以不写
                .upload();  //将文件上传到对应地方
        return fileInfo == null ? "上传失败！" : fileInfo.getUrl();

    }
}
