package com.pm.core.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.pm.core.bean.MetaObject;
import com.pm.core.dao.AbstractBaseDao;
import com.pm.core.dao.Dao;
import com.pm.core.util.DateUtil;
import com.pm.core.util.SqlserverConnUtil;

@Repository
@SuppressWarnings("rawtypes")
public class DaoImpl extends AbstractBaseDao implements Dao {

    public void save(MetaObject metaObject) {
        if (metaObject.getId() != null && metaObject.getId().equals("")) {
            metaObject.setId(null);
        }
        if (metaObject.getCreateDate() == null) {
            metaObject.setCreateDate(DateUtil.getDate());
        }
        metaObject.setUpdateDate(DateUtil.getDate());
        getSession().saveOrUpdate(metaObject);
    }

    public void remove(MetaObject metaObject) {
        getSession().delete(metaObject);
    }

    @SuppressWarnings("unchecked")
    public MetaObject get(Class c, String id) {
        return (MetaObject) getSession().get(c, id);
    }

    public List list(Class c) {
        return getQueryList("from " + c.getName() + " order by createDate", null);
    }

    public Query getQuery(String sql, Object[] objects) {
        Query query = getSession().createQuery(sql);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                query.setParameter(i, objects[i]);
            }
        }
        return query;
    }

    public SQLQuery getSQLQuery(String sql, Object[] objects) {
        SQLQuery query = getSession().createSQLQuery(sql);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                query.setParameter(i, objects[i]);
            }
        }
        return query;
    }

    public List getQueryList(String sql, Object[] objects) {
        return getQuery(sql, objects).list();
    }

    public Object getQueryObject(String sql, Object[] objects) {
        return getQuery(sql, objects).uniqueResult();
    }

    public List getQueryPageList(String sql, Object[] objects, int startSize, int pageSize) {
        Query query = getQuery(sql, objects);
        query.setFirstResult(startSize);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public int getQueryPageSize(String sql, Object[] objects) {
        int indexFrom = sql.toUpperCase().indexOf("FROM");
        if (indexFrom > 0) {
            sql = sql.substring(indexFrom);
        }
        int indexOrder = sql.toUpperCase().indexOf("ORDER");
        if (indexOrder > 0) {
            sql = sql.substring(0, indexOrder);
        }
        Query query = getQuery("select count(*) " + sql, objects);
        return ((Long) query.uniqueResult()).intValue();
    }

    public List getSQLQueryPageList(String sql, Object[] objects, int startSize, int pageSize) {
        SQLQuery query = getSQLQuery(sql, objects);
        query.setFirstResult(startSize);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public int getSQLQueryPageSize(String sql, Object[] objects) {
        SQLQuery query = getSQLQuery("select count(*) from (" + sql + ") as A", objects);
        return ((Integer) query.uniqueResult()).intValue();
    }
    
    public int getSQLQueryPageSizes(String sql,Object[] objects){
        int indexOrder = sql.toUpperCase().lastIndexOf("ORDER BY");
        if (indexOrder > 0) {
            sql = sql.substring(0, indexOrder);
        }
        SQLQuery query = getSQLQuery("select count(*) from (" + sql+") as A", objects);
        return ((Integer) query.uniqueResult()).intValue();
    }
}
