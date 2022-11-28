package com.yww.admin.system.controller;

import com.yww.admin.common.Result;
import com.yww.admin.utils.AssertUtils;
import com.yww.admin.utils.FileUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * <p>
 * 文件接口
 * </p>
 *
 * @Author yww
 * @Date 2022/11/21 17:38
 */
@Slf4j
@Tag(name = "文件接口")
@RestController
@RequestMapping("/")
public class FileController {

    @Operation(summary = "获取文件", description = "获取文件")
    @GetMapping(value = "/file/**")
    public ResponseEntity<Resource> download(HttpServletRequest request) {
        File file = new File(request.getRequestURI());
        AssertUtils.isTrue(file.exists(), "访问文件不存在");
        String contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(request.getRequestURI())
                .build().toString();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));
    }

    @Operation(summary = "上传文件", description = "上传文件")
    @RequestMapping(value = "/uploadFile")
    public Result<String> uploadFile(@RequestPart MultipartFile file) {
        log.info("文件入口,上传开始：{}", "");
        return Result.success(FileUtil.saveFile(file));
    }

}
