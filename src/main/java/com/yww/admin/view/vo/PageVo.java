package com.yww.admin.view.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.yww.admin.view.request.PageRequest;

import java.util.List;

/**
 * <p>
 * 分页查询结果
 * </p>
 *
 * @ClassName PageResultVo
 * @Author yww
 * @Date 2022/10/24 21:15
 */
@SuppressWarnings("all")
@JsonPropertyOrder({"start", "size", "total", "pageCount", "rows"})
public class PageVo<T> {

    /**
     * 本页记录在所有记录中的起始位置
     */
    private int start;

    /**
     * 每页记录条数
     */
    private int size;

    /**
     * 总记录条数
     */
    private int total;

    /**
     *  总页数
     */
    private int pageCount;

    /**
     * 当前页数据
     */
    private List<T> rows;

    public PageVo() {}

    public static <E> PageVo<E> ofReqVo(PageRequest reqVo, List<E> rows, int total) {
        PageVo<E> pageVo = new PageVo<>();
        pageVo.setSize(reqVo.getSize());
        pageVo.setStart(reqVo.getOffset());
        pageVo.setTotal(total);
        pageVo.setPageCount(total / reqVo.getSize() + 1);
        pageVo.setRows(rows);
        return pageVo;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
