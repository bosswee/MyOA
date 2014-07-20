package com.wee.oa.base;

import java.util.List;

import com.wee.oa.domain.PageBean;
import com.wee.oa.util.QueryHelper;

public interface DaoSupport<T> {
	/**
	 * 保存实体
	 * @param entity
	 */
	void save(T entity);
   /**
    * 删除实体
    * @param entity
    */
	void delete(Long id);
	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	T getById(Long id);
	/**
	 * 根据id列表查询实体
	 * @param ids
	 * @return
	 */
	
	List<T> getByIds(Long[] ids);
	/**
	 * 查询所有实体
	 */
	
	List<T> findAll();
	
	PageBean getPageBean(int currentPage, String hql, Object[] args);
	PageBean getPageBean(int currentPage, QueryHelper queryHelper);

}
