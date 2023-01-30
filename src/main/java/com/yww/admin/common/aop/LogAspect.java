package com.yww.admin.common.aop;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.yww.admin.system.entity.Log;
import com.yww.admin.system.service.impl.LogServiceImpl;
import com.yww.admin.utils.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(com.yww.admin.annotation.Log.class);
    private final LogServiceImpl logService;

    @Autowired
    public LogAspect(LogServiceImpl logService) {
        this.logService = logService;
    }

    /**
     * 声明切入点
     */
    @Pointcut("@annotation(com.yww.admin.annotation.Log)")
    public void pointCut() {}

    /**
     * 环绕通知
     */
    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        // 获取方法
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 获取Log注解的信息，查看是否需要保存操作记录
        boolean isSave = false;
        String value = "";
        if (method.isAnnotationPresent(com.yww.admin.annotation.Log.class)) {
            com.yww.admin.annotation.Log annotation = method.getAnnotation(com.yww.admin.annotation.Log.class);
            isSave = annotation.save();
            value = annotation.value();
        }
        // 打印注解上的消息
        if (StrUtil.isNotBlank(value)) {
            LOGGER.info(value);
        }
        // 若是选择保存到数据库，则获取信息后保存
        if (!isSave) {
            return result;
        }
        long startTime = System.currentTimeMillis();
        // 获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return result;
        }
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息
        Log.LogBuilder builder = Log.builder();
        // 获取Operation的注解信息
        if (method.isAnnotationPresent(Operation.class)) {
            Operation operation = method.getAnnotation(Operation.class);
            builder.summary(operation.summary()).description(operation.description());
        }
        long endTime = System.currentTimeMillis();
        String urlStr = request.getRequestURL().toString();
        builder.basePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()))
                .uri(request.getRequestURI())
                .url(request.getRequestURL().toString())
                .browser(IpUtil.getBrowser(request).getBrowser().getName())
                .ip(IpUtil.getIpAddr(request))
                .method(request.getMethod())
                .parameter(JSONUtil.parse(getParameter(method,  joinPoint.getArgs())).toString())
                .result(JSONUtil.parse(result).toString())
                .spendTime((int) (endTime - startTime))
                .startTime(LocalDateTimeUtil.of(startTime));
        Log log = builder.build();
        LOGGER.info(log.toString());
        logService.save(log);
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     *
     * @param method 方法
     * @param args   传入参数
     * @return 请求参数
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
