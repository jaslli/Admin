package com.yww.management.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *      IP相关工具类
 * </p>
 *
 * @ClassName IpUtil
 * @Author yww
 * @Date 2022/10/16 14:27
 */
public class IpUtil {

    /**
     * 获取客户端IP
     *
     * @param request   请求
     * @return          IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        // 判断x-forwarded-for是否有IP
        String ip = request.getHeader("x-forwarded-for");
        if (isNotUnknown(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        // 判断Proxy-Client-IP是否有IP
        if (isNotUnknown(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        // 判断X-Forwarded-For是否有IP
        if (isNotUnknown(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        // 判断WL-Proxy-Client-IP是否有IP
        if (isNotUnknown(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        // 判断X-Real-IP是否有IP
        if (isNotUnknown(ip)) {
            ip = request.getRemoteAddr();
        }
        // 若是IP最后为0:0:0:0:0:0:0:1，就是本地回环地址，否则第一个非unknown的IP地址就是真实地址
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : getMultistageReverseProxyIp(ip);
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    @SuppressWarnings("all")
    public static String getMultistageReverseProxyIp(String ip) {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(",") > 0) {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips) {
                if (isNotUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关
     *
     * @param checkString 被检测的字符串
     * @return 是否未知
     */
    public static boolean isNotUnknown(String checkString) {
        return StrUtil.isNotBlank(checkString) && StrUtil.isNotEmpty(checkString) && !"unknown".equalsIgnoreCase(checkString);
    }

    /**
     * 获取UserAgent信息对象
     *
     * @param request 请求
     * @return UserAgent
     */
    public static UserAgent getBrowser(HttpServletRequest request) {
        return UserAgentUtil.parse(request.getHeader("User-Agent"));
    }

}
