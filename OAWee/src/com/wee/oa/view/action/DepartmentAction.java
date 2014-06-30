package com.wee.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wee.oa.base.BaseAction;
import com.wee.oa.domain.Department;
import com.wee.oa.service.DepartmentService;
import com.wee.oa.util.DepartmentUtils;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>  {

	

	

	private Long parentId;

	

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 返回列表
	 * 
	 * @return
	 */
	public String list() {
		List<Department> departmentList = null;
		if(parentId==null){
			departmentList = departmentService.findTopList();
		}else{
			departmentList = departmentService.findChildrenList(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/**
	 * delete
	 * 
	 * @return
	 */

	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	/**
	 * add interface
	 * 
	 * @return
	 */
	public String addUI() throws Exception {
		//List<Department> departmentList = departmentService.findAll();
		//ActionContext.getContext().put("departmentList", departmentList);

		List<Department> topList =departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList,null);
		ActionContext.getContext().put("departmentList", departmentList);
		
		
		
		
		
		return "addUI";
	}

	/**
	 * add return to list
	 * 
	 * @return
	 */

	public String add() throws Exception {
		// 封装对象

		// 设置上级属性
		Department parent = departmentService.getById(parentId);
		model.setParent(parent);

		System.out.println(model.getName());
		departmentService.save(model);

		return "toList";
	}

	/**
	 * edit interface
	 * 
	 * @return
	 */

	public String editUI() throws Exception {
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);

		//List<Department> departmentList = departmentService.findAll();
		//ActionContext.getContext().put("departmentList", departmentList);
		
		List<Department> topList =departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList,department);
		ActionContext.getContext().put("departmentList", departmentList);
		
		
		if (department.getParent() != null) {
			this.parentId = department.getParent().getId();
		}
		return "editUI";
	}

	/**
	 * edit return to list
	 * 
	 * @return
	 */

	public String edit() throws Exception {
		
		Department department = departmentService.getById(model.getId());
		
		department.setName(model.getName());
		department.setDescription(model.getDescription());
         
		
		Department parent = departmentService.getById(parentId);
		
		department.setParent(parent);
		departmentService.update(department);
		return "toList";
	}

}
