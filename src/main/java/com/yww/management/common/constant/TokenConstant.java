package com.yww.management.common.constant;

/**
 * <p>
 *      Token相关常量类
 * </p>
 *
 * @ClassName TokenConstant
 * @Author chenhao
 * @Date 2022/10/20 20:52
 */
public class TokenConstant {

    /**
     *  Token的密钥
     */
    public static final String TOKEN_SECRET = "wslioy";

    /**
     *  Token在请求头部的名称
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     *  Token的前缀
     */
    public static final String TOKEN_PREFIX = "wslioy";

    /**
     *  header的头部加密算法声明
     */
    public static final String TOKEN_ALG = "HMAC512";

    /**
     *  header的头部Token类型
     */
    public static final String TOKEN_TYP = "JWT";

    /**
     *  Token的签发者
     */
    public static final String TOKEN_ISSUER = "yww";

    /**
     *  Token面向的主体
     */
    public static final String TOKEN_SUBJECT = "management";

    /**
     *  Token的接收方
     */
    public static final String TOKEN_AUDIENCE = "vue-management";

}
