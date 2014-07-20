package com.wee.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.wee.oa.cfg.Configuration;
import com.wee.oa.domain.PageBean;
import com.wee.oa.util.QueryHelper;

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

	@Override
	/*
	 * 实现过滤条1.0(non-Javadoc)
	 * @see com.wee.oa.base.DaoSupport#getPageBean(int, java.lang.String, java.lang.Object[])
	 */
	public PageBean getPageBean(int currentPage, String hql, Object[] args) {
		
		//拿到配置中的pageSize
				int pageSize=Configuration.getPageSize();
				
				//拿到recordlist和recordCount
				Query query =  getSession().createQuery(hql);
				if(args!=null&args.length>0){
					for(int i=0;i<args.length;i++){
						query.setParameter(i,args[i]);
					}
				}
				
				List list =  query
						.setFirstResult((currentPage-1)*pageSize)
						.setMaxResults(pageSize).list();
				
				query= getSession().createQuery(//
						"SELECT COUNT(*) " + hql);
				if(args!=null&args.length>0){
					for(int i=0;i<args.length;i++){
						query.setParameter(i,args[i]);
					}}
					Long count = (Long) query.uniqueResult();
						
				
				return new PageBean(count.intValue(),list,currentPage,pageSize);
			}
	/**
	 * 公共的查询分页信息的方法（最终版）
	 * 
	 * @param pageNum
	 * @param queryHelper
	 *            查询语句 + 参数列表
	 * @return
	 */
	public PageBean getPageBean(int currentPage, QueryHelper queryHelper) {
		System.out.println("------------> DaoSupportImpl.getPageBean( intcurrentPage, QueryHelper queryHelper )");

		// 获取pageSize等信息
		int pageSize = Configuration.getPageSize();
		List<Object> parameters = queryHelper.getParameters();

		// 查询一页的数据列表
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		List list = query.list(); // 查询

		// 查询总记录数
		query = getSession().createQuery(queryHelper.getQueryCountHql()); // 注意空格！
		if (parameters != null && parameters.size() > 0) { // 设置参数
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		Long count = (Long) query.uniqueResult(); // 查询

		return new PageBean(count.intValue(),list,currentPage,pageSize);
	}
	}
