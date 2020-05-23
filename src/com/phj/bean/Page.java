package com.phj.bean;

import java.util.List;

/**
 * @ClassName Page 分页抽取出来的显示模型
 * @Description: TODO
 * @Author 31637
 * @Date 2020/4/20
 * @Version V1.0
 **/
public class Page<T> {
    /**
     * 是否有下一页，依赖总页数计算
     */
    private boolean hasNext;
    /**
     * 是否有上一页，无依赖计算
     */
    private boolean hasPrevious;
    /**
     * 当前页数，用户传入
     */
    private int pageNow;
    /**
     * 总页数，依赖总记录数计算
     */
    private int totalPages;
    /**
     * 总记录数，查询数据库
     */
    private int totalCount;
    /**
     * 每页显示多少条记录,默认为4，用户传入设置
     */
    private int pageSize=4;
    /**
     * 数据库查询的起始索引，依赖当前页数和每页显示多少数据计算
     */
    private int index;
    /**
     * 封装当前页面数据，查询数据库
     */
    List<T> pageData;


    public Page() {
    }

    public Page(boolean hasNext, boolean hasPrevious, int pageNow, int totalPages, int totalCount, int pageSize, int index, List<T> pageData) {
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.pageNow = pageNow;
        this.totalPages = totalPages;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.index = index;
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "Page{" +
                "hasNext=" + isHasNext() +
                ", hasPrevious=" + isHasPrevious() +
                ", pageNow=" + pageNow +
                ", totalPages=" + getTotalPages() +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", index=" + index +
                ", pageData=" + pageData +
                '}';
    }

    public boolean isHasNext() {
        return getTotalPages() > getPageNow();
    }

    public boolean isHasPrevious() {
        return getPageNow()>1;
    }

    public int getPageNow() {
        return pageNow;
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
        if (pageNow <1){
            this.pageNow = 1;
        }
        if (pageNow > getTotalPages()){
            this.pageNow = getTotalPages();
        }
    }

    public int getTotalPages() {
        totalPages = getTotalCount()/getPageSize();
        if(getTotalCount()%getPageSize() != 0){
            totalPages = totalPages+1;
        }
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndex() {
        return getPageSize()*(getPageNow()-1);
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
