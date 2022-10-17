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

    /**
     * 生成Token
     *
     * @return  Token
     */
    public static String genToken(String secret) {
        // 设置Token头部（不设置也会默认有这两个值）
        Map<String, Object> header = new HashMap<String, Object>(2) {
            private static final long serialVersionUID = 1L;
            {
                put("alg", "HMAC512");
                put("typ", "JWT");
            }
        };
        // 设置需要放在Token的有效载荷
        Map<String, Object> payload = new HashMap<String, Object>(3) {
            private static final long serialVersionUID = 1L;
            {
                put("name", "yww");
                put("num", 1141950370);
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
                .sign(Algorithm.HMAC512(secret));
    }

    /**
     * 解析Token
     *
     * @param token Token
     */
    public static void parse(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512("1")).build();
            DecodedJWT decode = jwtVerifier.verify(token);
            System.out.println(decode.getHeader());
            System.out.println(decode.getPayload());
            System.out.println(decode.getSignature());
        } catch (AlgorithmMismatchException e) {
            System.out.println("签名算法不匹配");
        } catch (SignatureVerificationException e) {
            System.out.println("签名无效");
        } catch (TokenExpiredException e) {
            System.out.println("令牌已过期");
        } catch (MissingClaimException e) {
            System.out.println("缺少要验证的声明");
        } catch (IncorrectClaimException e) {
            System.out.println("声明包含的值和预期不符合");
        } catch (JWTVerificationException e) {
            System.out.println("验证中的某一个步骤失败");
        }
    }

}
