package com.wee.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wee.oa.base.DaoSupportImpl;
import com.wee.oa.domain.Department;
import com.wee.oa.service.DepartmentService;


@Service
//@Transactional
public  class DepartmentServiceImpl extends DaoSupportImpl<Department> implements DepartmentService {

	//@Resource
	//private DepartmentDao departmentDao;

	/**
	 
	@Override
	public List<Department> findAll() {

		return departmentDao.findAll();
	}

	@Override
	public void delete(Long id) {
		 departmentDao.delete(id);

	}

	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	public Department getById(Long id) {
		
		return departmentDao.getById(id);
	}

	@Override
	public void update(Department department) {
		 departmentDao.update(department);

	}
	* 
	 */

	
	@Override
	public List<Department> findTopList() {
		return getSession().createQuery(
				"FROM Department d WHERE d.parent=NULL")
				.list();
		
	}

	@Override
	public List<Department> findChildrenList(Long parentId) {
		return getSession().createQuery(

				"FROM Department d WHERE d.parent.id=?")
				.setParameter(0, parentId)
				.list();
	}

	

}
