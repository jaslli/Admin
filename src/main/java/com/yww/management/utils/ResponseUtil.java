package com.yww.management.utils;

import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *      用于接口外响应JSON的响应工具类
 * </p>
 *
 * @ClassName ResponseUtil
 * @Author yww
 * @Date 2022/10/12 21:30
 */
public class ResponseUtil {

    /**
     * 用于返回JSON数据
     * @param response 请求响应
     * @param result   响应封装
     */
    public static void response(HttpServletResponse response, Result<Object> result) throws IOException {
        // 设置响应的Header
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 设置响应码
        response.setStatus(result.getCode());
        // 设置响应内容
        response.getWriter().write(JSONUtil.toJsonStr(result));
        // 将缓存信息刷新到页面
        response.getWriter().flush();
    }

}
