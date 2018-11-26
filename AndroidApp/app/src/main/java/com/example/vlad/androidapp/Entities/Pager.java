package com.example.vlad.androidapp.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pager {

    @SerializedName("records_per_page")
    @Expose
    private Integer recordsPerPage;
    @SerializedName("total_record_count")
    @Expose
    private Integer totalRecordCount;
    @SerializedName("current_page_record_count")
    @Expose
    private Integer currentPageRecordCount;
    @SerializedName("is_first_page")
    @Expose
    private Boolean isFirstPage;
    @SerializedName("is_final_page")
    @Expose
    private Boolean isFinalPage;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("current_page_path")
    @Expose
    private String currentPagePath;
    @SerializedName("next_page")
    @Expose
    private Integer nextPage;
    @SerializedName("next_page_path")
    @Expose
    private String nextPagePath;
    @SerializedName("previous_page")
    @Expose
    private Object previousPage;
    @SerializedName("previous_page_path")
    @Expose
    private Object previousPagePath;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_pages_path")
    @Expose
    private String totalPagesPath;

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public Integer getCurrentPageRecordCount() {
        return currentPageRecordCount;
    }

    public Boolean getIsFirstPage() {
        return isFirstPage;
    }

    public Boolean getIsFinalPage() {
        return isFinalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public String getCurrentPagePath() {
        return currentPagePath;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public String getNextPagePath() {
        return nextPagePath;
    }

    public Object getPreviousPage() {
        return previousPage;
    }

    public Object getPreviousPagePath() {
        return previousPagePath;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public String getTotalPagesPath() {
        return totalPagesPath;
    }
}