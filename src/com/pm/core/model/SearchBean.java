package com.pm.core.model;

/**
 * SearchBean查询封装类
 * 
 * @author: FYL
 */

public class SearchBean {

    /** 当前页码 */
    private int currentPage;

    /** 每页记录 */
    private int pageSize;

    /** 排序字段 */
    private String searchOrderName;

    /** 排序方式 */
    private boolean searchOrderType;

    //-----------------------------------STRING查询参数----------------------------------------

    /** 查询参数1 */
    private String searchName1;

    /** 查询参数2 */
    private String searchName2;

    /** 查询参数3 */
    private String searchName3;

    /** 查询参数4 */
    private String searchName4;

    /** 查询参数5 */
    private String searchName5;

    //-----------------------------------INT查询参数----------------------------------------

    /** 查询参数1 */
    private int searchInt1;

    /** 查询参数2 */
    private int searchInt2;

    /** 查询参数3 */
    private int searchInt3;

    /** 查询参数4 */
    private int searchInt4;

    /** 查询参数5 */
    private int searchInt5;

    //-----------------------------------DATE查询参数----------------------------------------

    /** 查询Date参数1 */
    private String searchDate1;

    /** 查询Date参数1 */
    private String searchDate2;

    /** 查询Date参数1 */
    private String searchDate3;

    /** 查询Date参数1 */
    private String searchDate4;

    /** 查询Date参数1 */
    private String searchDate5;

    /** 查询Date参数1 */
    private String searchDate6;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchOrderName() {
        return searchOrderName;
    }

    public void setSearchOrderName(String searchOrderName) {
        this.searchOrderName = searchOrderName;
    }

    public boolean getSearchOrderType() {
        return searchOrderType;
    }

    public void setSearchOrderType(boolean searchOrderType) {
        this.searchOrderType = searchOrderType;
    }

    public String getSearchName1() {
        return searchName1;
    }

    public void setSearchName1(String searchName1) {
        this.searchName1 = searchName1;
    }

    public String getSearchName2() {
        return searchName2;
    }

    public void setSearchName2(String searchName2) {
        this.searchName2 = searchName2;
    }

    public String getSearchName3() {
        return searchName3;
    }

    public void setSearchName3(String searchName3) {
        this.searchName3 = searchName3;
    }

    public String getSearchName4() {
        return searchName4;
    }

    public void setSearchName4(String searchName4) {
        this.searchName4 = searchName4;
    }

    public String getSearchName5() {
        return searchName5;
    }

    public void setSearchName5(String searchName5) {
        this.searchName5 = searchName5;
    }

    public int getSearchInt1() {
        return searchInt1;
    }

    public void setSearchInt1(int searchInt1) {
        this.searchInt1 = searchInt1;
    }

    public int getSearchInt2() {
        return searchInt2;
    }

    public void setSearchInt2(int searchInt2) {
        this.searchInt2 = searchInt2;
    }

    public int getSearchInt3() {
        return searchInt3;
    }

    public void setSearchInt3(int searchInt3) {
        this.searchInt3 = searchInt3;
    }

    public int getSearchInt4() {
        return searchInt4;
    }

    public void setSearchInt4(int searchInt4) {
        this.searchInt4 = searchInt4;
    }

    public int getSearchInt5() {
        return searchInt5;
    }

    public void setSearchInt5(int searchInt5) {
        this.searchInt5 = searchInt5;
    }

    public String getSearchDate1() {
        return searchDate1;
    }

    public void setSearchDate1(String searchDate1) {
        this.searchDate1 = searchDate1;
    }

    public String getSearchDate2() {
        return searchDate2;
    }

    public void setSearchDate2(String searchDate2) {
        this.searchDate2 = searchDate2;
    }

    public String getSearchDate3() {
        return searchDate3;
    }

    public void setSearchDate3(String searchDate3) {
        this.searchDate3 = searchDate3;
    }

    public String getSearchDate4() {
        return searchDate4;
    }

    public void setSearchDate4(String searchDate4) {
        this.searchDate4 = searchDate4;
    }

    public String getSearchDate5() {
        return searchDate5;
    }

    public void setSearchDate5(String searchDate5) {
        this.searchDate5 = searchDate5;
    }

    public String getSearchDate6() {
        return searchDate6;
    }

    public void setSearchDate6(String searchDate6) {
        this.searchDate6 = searchDate6;
    }
}
