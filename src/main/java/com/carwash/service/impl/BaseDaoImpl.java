package com.carwash.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.service.BaseDaoI;

@Repository("baseDao")
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.REQUIRED)
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {
		Session currentSession = this.getCurrentSession();
		return currentSession.save(o);

	}

	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int from,
			int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}

		List<T> lists = q.setFirstResult(from).setMaxResults(rows).list();

		return lists;

	}

	@Override
	public List<T> find(String hql, int from, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult(from).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	/*
	 * orderBy字符串中格式为“字段 排序方式”如"id asc"
	 */
	@Override
	public T get(Class<T> c, String where, String orderBy, String... params) {
		List<T> list = find(c, where, orderBy, params);
		if (null == list || list.size() > 1) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * orderBy字符串中格式为“字段 排序方式”
	 */
	@Override
	public List<T> find(Class<T> c, String where, String orderBy,
			String... params) {
		if (null == params || params.length == 0) {
			return null;
		}
		Session currentSession = this.getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		for (String param : params) {
			sql.append(param);
			sql.append(",");
		}
		if (sql.toString().endsWith(",")) {
			sql.deleteCharAt(sql.toString().length() - 1);
		}
		sql.append(" from " + c.getAnnotation(Table.class).name());
		sql.append(" where " + where);
		if (null != orderBy && !"".equals(orderBy.trim())) {
			sql.append(" ORDER BY " + orderBy);
		}
		List<T> list = currentSession.createSQLQuery(sql.toString()).list();
		List<T> tlist = new ArrayList<T>();
		for (T t : list) {
			Object[] objs = (Object[]) t;
			try {
				T instance = c.newInstance();
				for (int i = 0; i < params.length; i++) {
					String param = params[i].substring(0, 1).toUpperCase()
							+ params[i].substring(1, params[i].length());
					Object obj = objs[i];

					Method[] methods = instance.getClass().getMethods();
					for (Method method : methods) {
						method.setAccessible(true);
						if (method.getName().equals("set" + param)) {
							Class<?>[] parameterTypes = method
									.getParameterTypes();
							if (parameterTypes.length != 1) {
								return null;
							}
							String paramTypeName = parameterTypes[0]
									.getSimpleName();

							if ("String".equals(paramTypeName)) {
								method.invoke(instance, obj == null ? null
										: obj.toString());
							} else if ("int".equals(paramTypeName)) {
								method.invoke(instance,
										Integer.valueOf(obj.toString()));
							} else if ("Date".equals(paramTypeName)) {
								method.invoke(instance, obj == null ? null
										: (Date) obj);
							} else if ("double".equals(paramTypeName)) {
								method.invoke(instance,
										Double.valueOf(obj.toString()));
							} else if ("Integer".equals(paramTypeName)) {
								method.invoke(instance, obj == null ? null
										: Integer.valueOf(obj.toString()));
							}
						}
					}
				}
				tlist.add(instance);
			} catch (Exception e) {
				logger.error("数据库异常", e);
				e.printStackTrace();
				return null;
			}
		}

		return tlist;
	}

	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	/*
	*
	 */
	@Override
	public BigInteger countSql(String sql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}

	/*
	*
	 */
	@Override
	public int executeSql(String sql) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}
}
