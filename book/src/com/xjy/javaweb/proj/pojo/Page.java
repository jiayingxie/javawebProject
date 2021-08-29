package com.xjy.javaweb.proj.pojo;

import java.util.List;

/**
 * @Author Jiaying Xie
 * @Description: Page is the model of paging, <T> is the specific JavaBean Object
 */
public class Page<T> {
    // how many item in one page
    public static final Integer PAGE_SIZE = 4;

    // current page number
    private Integer pageNo;

    // total page number
    private Integer pageTotal;

    // how many item in one page; 当前页显示数量
    private Integer pageSize = PAGE_SIZE;

    // total items
    private Integer pageTotalCount;

    // the current page's items' details
    private List<T> items;

    // 分页条的请求地址
    private String url;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        // dealing with the case that page number is invalid
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
