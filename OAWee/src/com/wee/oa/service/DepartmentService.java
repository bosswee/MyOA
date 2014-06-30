package com.wee.oa.service;

import java.util.List;

import com.wee.oa.base.DaoSupport;
import com.wee.oa.domain.Department;
import com.wee.oa.domain.Role;

public interface DepartmentService extends DaoSupport<Department> {
	
	

	List<Department> findTopList();

	List<Department> findChildrenList(Long parentId);
	

}
