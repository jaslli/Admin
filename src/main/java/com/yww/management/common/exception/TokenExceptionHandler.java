package com.yww.management.common.exception;

import com.auth0.jwt.exceptions.*;
import com.yww.management.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *      Token验证的异常处理
 * </p>
 *
 * @ClassName TokenExceptionHandler
 * @Author yww
 * @Date 2022/10/18 23:03
 */
@RestControllerAdvice
public class TokenExceptionHandler {

    /**
     * 处理签名算法不匹配的异常信息
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = AlgorithmMismatchException.class)
    public Result<String> algorithmMismatchExceptionHandler(AlgorithmMismatchException e) {
        return Result.failure(500, "签名算法不匹配" ,e.getMessage());
    }

    /**
     * 处理签名无效的异常信息
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = SignatureVerificationException.class)
    public Result<String> signatureVerificationExceptionHandler(SignatureVerificationException e) {
        return Result.failure(500, "签名无效" ,e.getMessage());
    }

    /**
     * 处理令牌已过期的异常信息
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = TokenExpiredException.class)
    public Result<String> tokenExpiredExceptionHandler(TokenExpiredException e) {
        return Result.failure(500, "令牌已过期" ,e.getMessage());
    }

    /**
     * 处理缺少要验证的声明的异常信息
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = MissingClaimException.class)
    public Result<String> tMissingClaimExceptionHandler(MissingClaimException e) {
        return Result.failure(500, "缺少要验证的声明" ,e.getMessage());
    }

    /**
     * 处理声明包含的值和预期不符合的声明的异常信息
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = IncorrectClaimException.class)
    public Result<String> incorrectClaimExceptionHandler(IncorrectClaimException e) {
        return Result.failure(500, "声明包含的值和预期不符合" ,e.getMessage());
    }

    /**
     * 处理验证中的某一个步骤失败的声明的异常信息
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = JWTVerificationException.class)
    public Result<String> jwtVerificationExceptionHandler(JWTVerificationException e) {
        return Result.failure(500, "验证中的某一个步骤失败" ,e.getMessage());
    }

}
