package com.pm.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.pm.core.bean.MetaObject;

@SuppressWarnings("rawtypes")
public interface Dao {

    /**
     * 新增和修改
     * 
     * @param metaObject 对象
     */
    public void save(MetaObject metaObject);

    /**
     * 删除
     * 
     * @param metaObject 对象
     */

    public void remove(MetaObject metaObject);

    /**
     * 查询
     * 
     * @param Class Class对象
     * @param id 对象ID
     */
    public MetaObject get(Class c, String id);

    /**
     * 所有对象列表
     * 
     * @param Class 对象Class类
     */
    public List list(Class c);

    /**
     * Query对象
     * 
     * @param sql SQL语句
     * @param objects 参数
     */
    public Query getQuery(String sql, Object[] objects);
    
    /**
     * SQLQuery对象
     * 
     * @param sql SQL语句
     * @param objects 参数
     */
    public SQLQuery getSQLQuery(String sql, Object[] objects);

    /**
     * 查询列表
     * 
     * @param sql SQL语句
     * @param objects 参数
     */
    public List getQueryList(String sql, Object[] objects);

    /**
     * 查询Object
     * 
     * @param sql SQL语句
     * @param objects 参数
     */
    public Object getQueryObject(String sql, Object[] objects);

    /**
     * QueryPageList分页查询
     * 
     * @param sql SQL语句
     * @param objects 参数
     * @param startSize 开始条数
     * @param pageSize 每页大小
     */
    public List getQueryPageList(String sql, Object[] objects, int startSize, int pageSize);

    /**
     * QueryPageSize总记录条数
     * 
     * @param sql SQL语句
     * @param objects 参数
     */
    public int getQueryPageSize(String sql, Object[] objects);
    
    /**
     * SQLQueryPageList分页查询
     * 
     * @param sql SQL语句
     * @param objects 参数
     * @param startSize 开始条数
     * @param pageSize 每页大小
     */
    public List getSQLQueryPageList(String sql, Object[] objects, int startSize, int pageSize);

    /**
     * SQLQueryPageSize总记录条数
     * 
     * @param sql SQL语句
     * @param objects 参数
     */
    public int getSQLQueryPageSize(String sql, Object[] objects);
    /**
     * SQLQueryPageSize总记录条数
     * 
     * @param sql SQL语句
     * @param objects 参数
     */
    public int getSQLQueryPageSizes(String sql,Object[] objects);
}
