package com.pm.core.busin;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.pm.core.bean.MetaObject;

/**
 * 业务层处理方法
 * 
 * @author FYL
 */

@SuppressWarnings("rawtypes")
public interface BusinApi {

    /**
     * SpringBean的名称
     */
    public static String BUSINAPI = "businApiImpl";

    /**
     * 保存
     * 
     * @param metaObject 对象
     */
    void save(MetaObject metaObject);

    /**
     * 保存集合
     * 
     * @param list LIST
     */
    void save(List list);

    /**
     * 删除
     * 
     * @param metaObject 对象
     */
    void remove(MetaObject metaObject);

    /**
     * 删除集合
     * 
     * @param list List
     */
    void remove(List list);

    /**
     * 删除数组
     * 
     * @param list List
     */
    void remove(Class c, String[] ids);

    /**
     * 查询对象
     * 
     * @param c Class
     * @param id ID
     * @return 对象
     */
    MetaObject get(Class c, String id);

    /**
     * 查询集合
     * 
     * @param c Class
     * @return 集合
     */
    List getList(Class c);

    /**
     * 查询集合
     * 
     * @param className className
     * @return 集合
     */
    List getList(String className);

    /**
     * 获得Query对象
     * 
     * @param sql SQL
     * @param objects 参数
     * @return Query
     */
    Query getQuery(String sql, Object[] objects);
    
    /**
     * 获得SQLQuery对象
     * 
     * @param sql SQL
     * @param objects 参数
     * @return Query
     */
    SQLQuery getSQLQuery(String sql, Object[] objects);

    /**
     * 查询集合
     * 
     * @param sql SQL
     * @param objects 参数
     * @return 集合
     */
    List getQueryList(String sql, Object[] objects);

    /**
     * 查询，获得唯一对象
     * 
     * @param sql SQL
     * @param objects 参数
     * @return 对象
     */
    Object getQueryObject(String sql, Object[] objects);

    /**
     * 获得List中第一个对象
     * 
     * @param sql SQL
     * @param objects 参数
     * @return 查询对象
     */
    Object getQueryListObject(String sql, Object[] objects);

    /**
     * 查询分页总条数
     * 
     * @param sql SQl
     * @param objects 参数
     * @return 总条数
     */
    int getQueryPageSize(String sql, Object[] objects);
    
    /**
     * 自带排序的分页总条数
     * @param sql
     * @param objects
     * @return
     */
    int getSQLQueryPageSizes(String sql,Object[] objects);

    /**
     * 查询分页集合
     * 
     * @param sql SQl
     * @param objects 参数
     * @param startSize 开始条数
     * @param pageSize 每页显示条数
     * @return 集合
     */
    List getQueryPageList(String sql, Object[] objects, int startSize, int pageSize);
    
    /**
     * 查询分页总条数
     * 
     * @param sql SQl
     * @param objects 参数
     * @return 总条数
     */
    int getSQLQueryPageSize(String sql, Object[] objects);
    
    

    /**
     * 查询分页集合
     * 
     * @param sql SQl
     * @param objects 参数
     * @param startSize 开始条数
     * @param pageSize 每页显示条数
     * @return 集合
     */
    List getSQLQueryPageList(String sql, Object[] objects, int startSize, int pageSize);
    
    
    
    
    
}
