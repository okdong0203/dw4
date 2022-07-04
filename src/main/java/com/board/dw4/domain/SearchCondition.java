package com.board.dw4.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    private int page = 1; // 현재페이지
    private int pageSize = 10; // 페이지의 크기
    private String keyword = ""; // 검색
    private String option = ""; // 검색옵션

    public SearchCondition(){}

    public SearchCondition(int page, int pageSize, String keyword, String option){
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }

    public String getQueryString(Integer page){
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("keyword", keyword)
                .queryParam("option", option)
                .build().toString();
    }

    public String getQueryString(){
        return getQueryString(page);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (page-1) * 10;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
