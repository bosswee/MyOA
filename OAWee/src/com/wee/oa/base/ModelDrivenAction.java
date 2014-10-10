package com.wee.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wee.oa.domain.User;
import com.wee.oa.service.DepartmentService;
import com.wee.oa.service.ForumService;
import com.wee.oa.service.PrivilegeService;
import com.wee.oa.service.ReplyService;
import com.wee.oa.service.RoleService;
import com.wee.oa.service.TemplateService;
import com.wee.oa.service.TopicService;
import com.wee.oa.service.UserService;



@SuppressWarnings("serial")
public class ModelDrivenAction<T> extends BaseAction implements
		ModelDriven<T>{
   
	
	@Resource
	protected DepartmentService departmentService;
	
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected UserService userService;
	
	@Resource
	protected PrivilegeService privilegeService;
	
	@Resource
	protected ForumService forumService;
	
	@Resource
	protected TopicService topicService;
	
	@Resource
	protected ReplyService replyService;
	
	@Resource
	protected TemplateService templateService;
	
	protected T model ;
	
	
	public ModelDrivenAction() {
		
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
	

	public String getRequestIP() {
		
		return ServletActionContext.getRequest().getRemoteAddr();
	}

	public User getCurrentUser() {
	
		return (User)ActionContext.getContext().getSession().get("user");
	}
	
	protected int currentPage=1;
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
