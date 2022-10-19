package com.yww.management.common.aop;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.yww.management.entity.Log;
import com.yww.management.utils.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *      用户操作日志处理切面
 * </p>
 *
 * @ClassName LogAspect
 * @Author yww
 * @Date 2022/10/15 17:23
 */
@Order(1)
@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.yww.management.annotation.Log.class);

    /**
     * 用于指定AOP的切点，即为标注了@Log注解的方法
     */
    @Pointcut("@annotation(com.yww.management.annotation.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息
        Log log = new Log();
        Object result = joinPoint.proceed();
        // 获取方法
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(Operation.class)) {
            Operation operation = method.getAnnotation(Operation.class);
            log.setSummary(operation.summary());
//            log.setDescription(operation.description());
        }
        long endTime = System.currentTimeMillis();
        String urlStr = request.getRequestURL().toString();
        log.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        log.setIp(IpUtil.getIpAddr(request));
        log.setMethod(request.getMethod());
        log.setParameter(JSONUtil.parse(getParameter(method, joinPoint.getArgs())).toString());
        log.setResult(JSONUtil.parse(result).toString());
        log.setSpendTime((int) (endTime - startTime));
        log.setStartTime(LocalDateTimeUtil.of(startTime));
        log.setUri(request.getRequestURI());
        log.setUrl(request.getRequestURL().toString());
        LOGGER.info("{}", JSONUtil.parse(log));
        return result;
    }

    /**
     *  根据方法和传入的参数获取请求参数
     *
     * @param method    方法
     * @param args      传入参数
     * @return          请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argsList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            // 将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argsList.add(args[i]);
            }
            // 将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>(16);
                String key = parameters[i].getName();
                if (StrUtil.isNotBlank(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argsList.add(map);
            }
        }
        if (argsList.size() == 0) {
            return null;
        } else if (argsList.size() == 1) {
            return argsList.get(0);
        } else {
            return argsList;
        }
    }

}
