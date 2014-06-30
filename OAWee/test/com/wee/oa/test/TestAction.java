package com.wee.oa.test;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import com.opensymphony.xwork2.ActionSupport;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport {
	
	@Resource
	private TestService testService;
	@Override
	public String execute(){
		System.out.println("-------> TestAction.execute()");
		//System.out.println("-------> testService = " + testService);
		//testService.addUsers();
		return "success";
	}
}



