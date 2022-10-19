package com.yww.management.utils;

import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *      Token工具类
 * 1. Header,记录令牌类型和签名算法
 * 2. payload,携带用户信息
 *      (1) iss(issuer), 签发者
 *      (2) sub(subject), 面向的主体
 *      (3) aud(audience), 接收方
 *      (4) nbf(notBefore), 开始生效生效时间戳
 *      (5) exp(expiresAt), 过期时间戳
 *      (6) iat(issuedAt ), 签发时间
 *      (7) jti(jwtId), 唯一标识
 * 3. signature, 签名，防止Token被篡改
 * </p>
 *
 * @ClassName TokenUtil
 * @Author yww
 * @Date 2022/10/15 14:31
 */
public class TokenUtil {

    private static final String SECRET = "yww";

    /**
     * 生成Token
     *
     * @return  Token
     */
    public static String genToken(Map<String, Object> payload) {
        // 设置Token头部（不设置也会默认有这两个值）
        Map<String, Object> header = new HashMap<String, Object>(2) {
            private static final long serialVersionUID = 1L;
            {
                put("alg", "HMAC512");
                put("typ", "JWT");
            }
        };
        Date now = new Date();
        Date exp = new Date(now.getTime() + 10);
        return JWT.create()
                // 设置header
                .withHeader(header)
                // 设置payload
                .withIssuer("yww")
                .withSubject("management")
                .withAudience("vue-management")
                .withNotBefore(now)
                .withExpiresAt(exp)
                .withIssuedAt(now)
                .withJWTId(IdUtil.fastSimpleUUID())
                .withPayload(payload)
                // 签名
                .sign(Algorithm.HMAC512(SECRET));
    }

    /**
     * 解析Token
     *
     * @param token Token
     */
    public static void parse(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512("1")).build();
        DecodedJWT decode = jwtVerifier.verify(token);
    }

}
