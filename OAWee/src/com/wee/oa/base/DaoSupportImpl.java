package com.wee.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public class DaoSupportImpl<T> implements DaoSupport<T> {
	@Resource
	private SessionFactory sessionFactory;

	protected Class<T> clazz = null;

	public DaoSupportImpl() {

		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

	}

	/**
	 * 获取当前session
	 * 
	 * @return
	 */

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);

	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			return;
		}
		Object entity = getById(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	}

	@Override
	public void update(T entity) {

		getSession().update(entity);

	}

	@Override
	public T getById(Long id) {
		if(id==null){
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		if(ids==null || ids.length==0){
			return Collections.EMPTY_LIST;
		}
		return getSession().createQuery(

				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")
				.setParameterList("ids", ids)
				.list();

	}

	
	public List<T> findAll() {
		return getSession().createQuery(

		"FROM " + clazz.getSimpleName()).list();
	}
}
