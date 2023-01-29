package com.yww.admin.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.segments.NormalSegmentList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yww.admin.common.GlobalException;
import com.yww.admin.view.request.PageRequest;
import com.yww.admin.view.vo.PageVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *      分页工具类
 * </p>
 *
 * @ClassName PageUtil
 * @Author yww
 * @Date 2022/10/24 21:21
 */
@SuppressWarnings("all")
public class PageUtil {

    /**
     * mybatis-plus自带分页插件
     *
     * @param pageVo
     * @param mapper
     * @param clazz
     * @param <R>
     * @param <Q>
     * @return
     */
    public static <R, Q extends PageRequest> PageVo<R> paging(Q pageVo, BaseMapper<R> mapper, Class<R> clazz) {
        R entity;
        try {
            entity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new GlobalException("分页类型转换错误");
        }
        BeanUtil.copyProperties(pageVo, entity);
        IPage<R> page = new Page<>(pageVo.getPage(), pageVo.getSize());
        page = mapper.selectPage(page, Wrappers.lambdaQuery(entity));
        return PageVo.ofReqVo(pageVo, page.getRecords(), Long.valueOf(page.getTotal()).intValue());
    }

    /**
     * mybatis-plus自带分页插件
     *
     * @param pageVo
     * @param mapper
     * @param <R>
     * @param <Q>
     * @return
     */
    public static <R, Q extends PageRequest> PageVo<R> paging(Q pageVo, BaseMapper<R> mapper, LambdaQueryWrapper<R> lambdaQuery, Class<R> clazz) {
        R entity;
        try {
            entity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new GlobalException("分页类型转换错误");
        }

        // 已设置在lambdaQuery的字段实体类中不应该重复设置
        String[] whereFields = getWhereFields(lambdaQuery);
        BeanUtil.copyProperties(pageVo, entity, whereFields);

        lambdaQuery.setEntity(entity);

        IPage<R> page = new Page<>(pageVo.getPage(), pageVo.getSize());
        page = mapper.selectPage(page, lambdaQuery);
        return PageVo.ofReqVo(pageVo, page.getRecords(), Long.valueOf(page.getTotal()).intValue());
    }

    /**
     * 获取设置的条件字段名
     *
     * @param lambdaQuery
     * @param <R>
     * @return
     */
    public static <R> String[] getWhereFields(LambdaQueryWrapper<R> lambdaQuery) {
        List<String> fields = new ArrayList<>();

        MergeSegments expression = lambdaQuery.getExpression();
        NormalSegmentList normal = expression.getNormal();
        int index = 0;
        for (ISqlSegment iSqlSegment : normal) {
            if (index % 4 == 0) {
                String field = StrUtil.toCamelCase(iSqlSegment.getSqlSegment());
                fields.add(field);
            }
            index++;
        }

        return fields.toArray(new String[]{});
    }

}
