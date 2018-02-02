package com.gmail.vitaliapetsenak.shop.web.model;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    private String uri;
    private Long curPage;
    private Long firstPageNum;
    private Long lastPageNum;
    private Long count;
    private String first = "First";
    private String last = "Last";
    private List<Long> pages;

    public Pagination() {
        this.uri = "/";
        this.curPage = 1L;
        this.firstPageNum = 1L;
        this.lastPageNum = 1L;
        this.count = 0L;
        this.pages = new ArrayList<>();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
        this.lastPageNum = count;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Long getFirstPageNum() {
        return firstPageNum;
    }

    public void setFirstPageNum(Long firstPageNum) {
        this.firstPageNum = firstPageNum;
    }

    public Long getLastPageNum() {
        return lastPageNum;
    }

    public void setLastPageNum(Long lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public List<Long> getPages() {
        if (curPage < 3 && count <= 3) {
            pages.clear();
            for (int i = 1; i < count + 1; i++) {
                pages.add(Long.valueOf(i));
            }
        } else if (curPage < 3) {
            pages.clear();
            pages.add(1L);
            pages.add(2L);
            pages.add(3L);
        } else if (curPage == count && count > 3) {
            pages.clear();
            pages.add(curPage - 2);
            pages.add(curPage - 1);
            pages.add(curPage);
        } else {
            pages.clear();
            pages.add(curPage - 1);
            pages.add(curPage);
            pages.add(curPage + 1);
        }
        return pages;
    }
}
