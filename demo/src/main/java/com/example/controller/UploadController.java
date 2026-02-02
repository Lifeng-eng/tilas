package com.example.controller;

import com.example.common.Result;
import com.example.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Slf4j
public class UploadController {

    /*
    * 文件上传
    * */
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        log.info("文件上传开始 {}", file.getOriginalFilename());
        String url =  aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传结束 {}", url);
        return Result.success(url);

    }
}
