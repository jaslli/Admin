package com.yww.admin.utils;

import cn.hutool.core.lang.UUID;
import com.yww.admin.common.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 文件工具类
 * </p>
 *
 * @ClassName FileUtil
 * @Author chenhao
 * @Date 2022/11/21 17:31
 */
@Slf4j
public class FileUtil {

    private final static String DATE_FORMAT = "/yyyyMM/dd/";

    /**
     * 保存文件
     *
     * @return 文件路径
     */
    public static String saveFile(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            //取文件扩展名
            String ext = Objects.requireNonNull(originalFilename).substring(originalFilename.lastIndexOf(".") + 1);
            //生成新文件名
            String name = UUID.randomUUID() + "." + ext;
            String format = new SimpleDateFormat(DATE_FORMAT).format(new Date());
            File dest = new File("/file/" + format + name);
            FileUtils.writeByteArrayToFile(dest, file.getBytes());
            return "/file/" + format + name;
        } catch (IOException e) {
            log.error("文件保存失败：{}", e.getMessage());
            throw new GlobalException("文件保存失败");
        }
    }

}
