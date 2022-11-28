package com.yww.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.admin.system.entity.Log;
import com.yww.admin.system.mapper.LogMapper;
import com.yww.admin.system.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志实体类 服务实现类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
