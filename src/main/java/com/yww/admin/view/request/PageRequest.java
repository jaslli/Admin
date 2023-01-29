package com.yww.admin.view.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * <p>
 *      分页查询请求对象
 * </p>
 *
 * @ClassName PageVo
 * @Author yww
 * @Date 2022/10/24 21:18
 */
@SuppressWarnings("all")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageRequest {

    /**
     * 每页显示行数默认为10
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * 当前页码, 首页为1
     */
    @Schema(description = "当前页码, 默认为首页，首页为1")
    private int page = 1;

    /**
     * 每页记录条数
     */
    @Schema(description = "每页记录条数,默认为10")
    private int size = DEFAULT_SIZE;

    /**
     * 排序字段名,asc,desc
     */
    @Schema(description = "排序字段名,asc,desc")
    private String sort;

    /**
     * 排序方向
     */
    @Schema(description = "排序方向")
    private String dir;

    public PageRequest() {}

    public static PageRequest of(int page, int size) {
        PageRequest pageReqVo = new PageRequest();
        pageReqVo.setPage(page);
        pageReqVo.setSize(size);
        return pageReqVo;
    }

    @JsonIgnore
    public int getOffset() {
        return (getPage() - 1) * getSize();
    }

    public int getPage() {
        return page > 0 ? page : 1;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size > 0 ? size : DEFAULT_SIZE;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

}
