package com.yww.management.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 *      IP工具类
 * </p>
 *
 * @ClassName IpUtil
 * @Author yww
 * @Date 2022/10/16 14:27
 */
@SuppressWarnings("all")
public class IpUtil {

    /**
     *  获取访问者IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            // 通过HTTP代理或者负载均衡一般会有x-forwarded-for头部
            ipAddress = request.getHeader("x-forwarded-for");
            // apache的http代理时一般会加上Proxy-Client-IP请求头，WL-Proxy-Client-IP是它的weblogic插件加上的
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                // 根据网卡取本机配置的IP
                if (ipAddress.equals("127.0.0.1")) {
                    try {
                        ipAddress = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null) {
                if (ipAddress.contains(",")) {
                    return ipAddress.split(",")[0];
                } else {
                    return ipAddress;
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
