package com.pm.core.busin.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import com.pm.core.bean.MetaObject;
import com.pm.core.busin.BusinApi;
import com.pm.core.dao.Dao;

@Service
@SuppressWarnings("rawtypes")
public class BusinApiImpl implements BusinApi {

    @Autowired
    public Dao dao;

    public void save(MetaObject metaObject) {
        if (metaObject != null) {
            dao.save(metaObject);
        }
    }

    public void save(List list) {
        if (list != null) {
            for (Object item : list) {
                MetaObject metaObject = (MetaObject) item;
                this.save(metaObject);
            }
        }
    }

    public void remove(MetaObject metaObject) {
        if (metaObject != null) {
            dao.remove(metaObject);
        }
    }

    public void remove(List list) {
        if (list != null) {
            for (Object item : list) {
                MetaObject metaObject = (MetaObject) item;
                this.remove(metaObject);
            }
        }
    }

    public void remove(Class c, String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                this.remove(this.get(c, id));
            }
        }
    }

    public MetaObject get(Class c, String id) {
        return dao.get(ClassUtils.getUserClass(c), id);
    }

    public List getList(Class c) {
        return dao.list(ClassUtils.getUserClass(c));
    }

    public List getList(String className) {
        try {
            Class c = Class.forName(className);
            return this.getList(c);
        } catch (Exception e) {
            return null;
        }
    }

    public Query getQuery(String sql, Object[] objects) {
        return dao.getQuery(sql, objects);
    }
    
    public SQLQuery getSQLQuery(String sql, Object[] objects) {
        return dao.getSQLQuery(sql, objects);
    }

    public List getQueryList(String sql, Object[] objects) {
        return dao.getQueryList(sql, objects);
    }

    public Object getQueryObject(String sql, Object[] objects) {
        return dao.getQueryObject(sql, objects);
    }

    public Object getQueryListObject(String sql, Object[] objects) {
        Query query = dao.getQuery(sql, objects);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public int getQueryPageSize(String sql, Object[] objects) {
        return dao.getQueryPageSize(sql, objects);
    }

    public List getQueryPageList(String sql, Object[] objects, int startSize, int pageSize) {
        return dao.getQueryPageList(sql, objects, startSize, pageSize);
    }

    public int getSQLQueryPageSize(String sql, Object[] objects) {
        return dao.getSQLQueryPageSize(sql, objects);
    }
    
    public int getSQLQueryPageSizes(String sql,Object[] objects){
        return dao.getSQLQueryPageSizes(sql, objects);
    }

    public List getSQLQueryPageList(String sql, Object[] objects, int startSize, int pageSize) {
        return dao.getSQLQueryPageList(sql, objects, startSize, pageSize);
    }
}
