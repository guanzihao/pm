package com.pm.core.model;

import java.util.ArrayList;
import java.util.List;

import com.pm.core.busin.BusinApi;
import com.pm.core.util.DateUtil;
import com.pm.core.util.StringUtil;

/**
 * PageBean分页属性类
 * 
 * @author: FYL
 */

@SuppressWarnings("rawtypes")
public class PageBean {

    /** 当前页码 */
    private int currentPage = 1;

    /** 页码总数 */
    public int totalPage = 0;

    /** 每页记录 */
    private int pageSize = 10;

    /** 总记录数 */
    private int totalSize = 0;

    /** 开始记录 */
    private int startSize = 0;

    /** 下一页 */
    private int nextPage = 0;

    /** 上一页 */
    private int prePage = 0;

    /** 是否有下一页 */
    private boolean hasNextPage = false;

    /** 是否有前一页 */
    private boolean hasPrePage = false;

    /** SQL语句 */
    private StringBuffer querySql = new StringBuffer();

    /** SQL参数 */
    private List<Object> paraList = new ArrayList<Object>();

    /** 查询结果 */
    private List pageList;

    private SearchBean searchBean;

    /** 执行查询方法 */
    private BusinApi businApi;

    /**
     * 是否查询所有记录
     */
    private boolean searchAll = false;

    /**
     * 构造函数
     * 
     * @param querySql 执行的SQL语句
     * @param currentPage 当前页
     * @param pageSize 每页条数
     */
    public PageBean(SearchBean searchBean, BusinApi businApi) {
        if (searchBean != null) {
            this.searchBean = searchBean;
            if (searchBean.getPageSize() > 0) {
                this.pageSize = searchBean.getPageSize();
            }
            if (searchBean.getCurrentPage() > 0) {
                this.currentPage = searchBean.getCurrentPage();
            }
        }
        if (businApi != null) {
            this.businApi = businApi;
        }
    }

    /**
     * 添加SQL语句
     */
    public void addQuerySQL(String sql) {
        querySql.append(" " + sql);
    }

    /**
     * 添加查询条件,按照STRING查询
     * 
     * @param params 参数，可以是多个，用逗号隔开
     * @param data 页面传递的数值
     * @param isLike 是否模糊查询 true：'like' or false：'='
     */
    public void addQueryStr(String params, String data, boolean isLike) {
        if (!StringUtil.isEmpty(params) && !StringUtil.isEmpty(data)) {
            String[] queryParams = params.split(",");
            if (queryParams != null && queryParams.length > 0) {
                if (querySql.toString().toUpperCase().indexOf("WHERE") == -1) {
                    addQuerySQL("where (");
                } else {
                    addQuerySQL("and (");
                }
                for (int i = 0; i < queryParams.length; i++) {
                    if (i > 0) {
                        addQuerySQL("or");
                    }
                    addQuerySQL(queryParams[i] + (isLike ? " like" : " =") + " ?");
                    addParamsLike(data.trim());
                }
                addQuerySQL(")");
            }
        }
    }

    /**
     * 添加查询条件,按照INT查询
     */
    public void addQueryInt(String params, int data) {
        if (!StringUtil.isEmpty(params) && data > 0) {
            if (querySql.toString().toUpperCase().indexOf("WHERE") == -1) {
                addQuerySQL("where (");
            } else {
                addQuerySQL("and (");
            }
            addQuerySQL(params + " = ?");
            addParams(data);
            addQuerySQL(")");
        }
    }

    /**
     * 添加查询条件,按照DATE时间段查询
     */
    public void addQueryDate(String params, String date1, String date2) {
        if (!StringUtil.isEmpty(params) && (!StringUtil.isEmpty(date1) || !StringUtil.isEmpty(date2))) {
            if (querySql.toString().toUpperCase().indexOf("WHERE") == -1) {
                addQuerySQL("where (");
            } else {
                addQuerySQL("and (");
            }
            if (!StringUtil.isEmpty(date1)) {
                addQuerySQL(params + " > ?");
                addParams(DateUtil.getDate(date1, 0));
            }
            if (!StringUtil.isEmpty(date2)) {
                if (!StringUtil.isEmpty(date1)) {
                    addQuerySQL("and");
                }
                addQuerySQL(params + " < ?");
                addParams(DateUtil.getDate(date2, 1));
            }
            addQuerySQL(")");
        }
    }

    /**
     * 获得SQL参数
     * 
     * @return Object[] 参数
     */
    public Object[] getParams() {
        return paraList.toArray();
    }

    /**
     * 添加SQL参数
     * 
     * @param object 参数
     */
    public void addParams(Object object) {
        paraList.add(object);
    }

    /**
     * 添加SQL参数
     * 
     * @param object 参数
     */
    public void addParamsLike(String object) {
        addParams("%" + object + "%");
    }

    /**
     * 指定排序
     * 
     * @param name 排序字段
     * @param type 排序类型 true:desc
     */
    public void addOrderBy(String name, boolean type) {
        if (!StringUtil.isEmpty(name)) {
            int orderNum = querySql.toString().toUpperCase().indexOf("ORDER BY");
            if (orderNum > -1) {
                querySql = new StringBuffer(querySql.substring(0, orderNum));
            }
            if (type) {
                addQuerySQL(" order by " + name + " desc");
            } else {
                addQuerySQL(" order by " + name + " asc");
            }
        }
    }

    /**
     * 执行分页器
     * 
     * @param totalSize 总条数
     */
    public void executePage(int totalSize) {
        this.totalSize = totalSize;
        if ((totalSize % pageSize) == 0) {
            totalPage = totalSize / pageSize;
        } else {
            totalPage = totalSize / pageSize + 1;
        }
        if (currentPage >= totalPage) {
            hasNextPage = false;
            currentPage = totalPage;
        } else {
            hasNextPage = true;
        }
        if (currentPage <= 1) {
            hasPrePage = false;
            currentPage = 1;
        } else {
            hasPrePage = true;
        }
        startSize = (currentPage - 1) * pageSize;
        nextPage = currentPage + 1;
        if (nextPage >= totalPage) {
            nextPage = totalPage;
        }
        prePage = currentPage - 1;
        if (prePage <= 1) {
            prePage = 1;
        }
    }

    /**
     * 设置导出所有集合
     */
    public void setSearchAll(boolean searchAll) {
        this.searchAll = searchAll;
    }

    /**
     * 执行分页查询
     */
    public void query() {
        addOrderBy(searchBean.getSearchOrderName(), searchBean.getSearchOrderType());
        if (businApi != null) {
            executePage(businApi.getQueryPageSize(getQuerySql(), getParams()));
            if (this.searchAll) {
                this.pageSize = this.totalSize;
            }
            pageList = businApi.getQueryPageList(getQuerySql(), getParams(), startSize, pageSize);
        }
    }

    /**
     * 执行分页查询
     */
    public void sqlquery() {
        addOrderBy(searchBean.getSearchOrderName(), searchBean.getSearchOrderType());
        if (businApi != null) {
            
            executePage(businApi.getSQLQueryPageSize(getQuerySql(), getParams()));
            pageList = businApi.getSQLQueryPageList(getQuerySql(), getParams(), startSize, pageSize);
        }
    }
   
    public void sqlquerys(){
        addOrderBy(searchBean.getSearchOrderName(), searchBean.getSearchOrderType());
        if (businApi != null) {
            executePage(businApi.getSQLQueryPageSizes(getQuerySql(), getParams()));
            pageList = businApi.getSQLQueryPageList(getQuerySql(), getParams(), startSize, pageSize);
        }
    }
    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public int getStartSize() {
        return this.startSize;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPrePage() {
        return this.prePage;
    }

    public boolean getHasNextPage() {
        return this.hasNextPage;
    }

    public boolean getHasPrePage() {
        return this.hasPrePage;
    }

    public String getQuerySql() {
        return this.querySql.toString();
    }

    public List getPageList() {
        return this.pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public SearchBean getSearchBean() {
        return searchBean;
    }
}
