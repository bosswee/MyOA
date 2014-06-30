package com.wee.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.wee.oa.service.PrivilegeService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wee.oa.service.DepartmentService;
import com.wee.oa.service.RoleService;
import com.wee.oa.service.UserService;



@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>{
   
	
	@Resource
	protected DepartmentService departmentService;
	
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected UserService userService;
	
	@Resource
	protected PrivilegeService privilegeService;
	
	protected T model ;
	
	
	public BaseAction() {
		
		try {
	ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
	Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
	
	
		model = clazz.newInstance();
	} catch (InstantiationException e) {
			e.printStackTrace();
	} catch (IllegalAccessException e) {
				e.printStackTrace();
	}
	
	}
	
	@Override
	public T getModel() {

		return model;
	}
}
