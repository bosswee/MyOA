package com.wee.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wee.oa.base.ModelDrivenAction;
import com.wee.oa.domain.User;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LoginoutAction extends ModelDrivenAction<User> {

	public String loginUI(){
		
		
		return "loginUI";
	}
	
	
	public String login(){
		
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		
		if(user==null){
			addFieldError("login", "登录名或密码错误");
				return "loginUI";
		}
		
		
		
		ActionContext.getContext().getSession().put("user", user);
		
		
		return "toHome";
	}
	
	
	public String logout(){
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
}
