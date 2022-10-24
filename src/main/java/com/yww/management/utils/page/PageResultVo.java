package com.yww.management.utils.page;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * <p>
 *      分页查询结果
 * </p>
 *
 * @ClassName PageResultVo
 * @Author yww
 * @Date 2022/10/24 21:15
 */
@JsonPropertyOrder({"start", "size", "total", "rows"})
public class PageResultVo<T> {

    /**
     *  本页记录在所有记录中的起始位置
     */
    private int start;

    /**
     *  每页记录条数
     */
    private int size;

    /**
     *  总记录条数
     */
    private int total;

    /**
     *  当前页数据
     */
    private List<T> rows;

    public static <E> PageResultVo<E> ofReqVo(PageVo reqVo, List<E> rows, int total) {
        PageResultVo<E> pageResultVo = new PageResultVo<>();
        pageResultVo.setSize(reqVo.getSize());
        pageResultVo.setStart(reqVo.getOffset());
        pageResultVo.setTotal(total);
        pageResultVo.setRows(rows);
        return pageResultVo;
    }

    public PageResultVo() {
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

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
