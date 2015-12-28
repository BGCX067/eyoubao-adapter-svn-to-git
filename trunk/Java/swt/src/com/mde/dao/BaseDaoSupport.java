package com.mde.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.mde.model.Pagination;
import com.mde.model.Queryer;

@Repository
public class BaseDaoSupport extends HibernateDaoSupport
{
    public Serializable insert(Serializable entity)
    {
        return getHibernateTemplate().save(entity);
    }
    
    @SuppressWarnings({"unchecked"})
    public <T> T insert(Class<T> entityClass, Serializable entity)
    {
        return (T)insert(entity);
    }
    
    public void update(Serializable entity)
    {
        getHibernateTemplate().update(entity);
    }
    
    public void insertAll(Collection<? extends Serializable> entities)
    {
        getHibernateTemplate().saveOrUpdateAll(entities);
    }
    
    public void delete(Serializable entity)
    {
        getHibernateTemplate().delete(entity);
    }
    
    public <T extends Serializable> void deleteByID(Class<T> entityClass, Serializable id)
    {
        delete(getHibernateTemplate().load(entityClass, id));
    }
    
    public <T> T get(Class<T> entityClass, Serializable id)
    {
        return getHibernateTemplate().get(entityClass, id);
    }
    
    public <T> List<T> find(Class<T> entityClass)
    {
        String hql = "FROM " + entityClass.getSimpleName();
        return find(entityClass, hql);
    }
    
    @SuppressWarnings({"unchecked"})
    public <T> List<T> find(Class<T> entityClass, String hql)
    {
        return getHibernateTemplate().find(hql);
    }
    
    @SuppressWarnings({"unchecked"})
    public <T> List<T> find(Class<T> entityClass, String hql, Object... objects)
    {
        return getHibernateTemplate().find(hql, objects);
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> findByNamedParam(String hql, String[] paramNames, Object[] values, Class<T> entityClass)
    {
        return getHibernateTemplate().findByNamedParam(hql, paramNames, values);
    }
    
    public List<?> find(String hql, Object... objects)
    {
        return getHibernateTemplate().find(hql, objects);
    }
    
    public int execute(String hql)
    {
        return getHibernateTemplate().bulkUpdate(hql);
    }
    
    public int execute(String hql, Object... objects)
    {
        return getHibernateTemplate().bulkUpdate(hql, objects);
    }
    
    @SuppressWarnings("unchecked")
    public <T> Pagination<T> find(Queryer queryer, int pageNo, int pageSize, Class<T> clazz)
    {
        int totalCount = ((Number)find(queryer.getCountHql(), queryer.getParameters().toArray()).get(0)).intValue();
        Pagination<T> pagination = new Pagination<T>(pageNo, pageSize, totalCount);
        int minPageNo = 1;
        int maxPageNo = pagination.getTotalPage();
        pageNo = pageNo < minPageNo ? minPageNo : pageNo;
        pageNo = pageNo > maxPageNo ? maxPageNo : pageNo;
        pagination.setPageNo(pageNo);
        
        if (totalCount == 0)
        {
            List<T> records = Collections.emptyList();
            pagination.setRecords(records);
            return pagination;
        }
        
        int first = (pageNo - 1) * pageSize;
        Query query = getSession().createQuery(queryer.getHql());
        
        int index = 0;
        
        for (Object parameter : queryer.getParameters())
        {
            query.setParameter(index++, parameter);
        }
        
        query.setFirstResult(first);
        query.setMaxResults(pagination.getPageSize());
        List<T> list = query.list();
        pagination.setRecords(list);
        return pagination;
    }
    
    @Autowired
    protected final void autowiredSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
}
