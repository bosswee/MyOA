package com.wee.oa.view.action;

import java.util.HashSet;
import java.util.List;




import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wee.oa.base.ModelDrivenAction;
import com.wee.oa.domain.Privilege;
import com.wee.oa.domain.Role;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenAction<Role>{
	private Long[] privilegeIds;

	
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	/**
	 * 返回列表
	 * @return
	 */
	public String list(){
	    List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	/**
	 * delete
	 * @return
	 */
	
	        public String  delete()throws Exception{
	        	roleService.delete(model.getId());
		    return "toList";
	}
	/**
	 * add interface
	 * @return
	 */
	public String  addUI() throws Exception{
		
		return "addUI";
	}
	/**
	 * add return to list
	 * @return
	 */
	
	public String add()throws Exception{
		roleService.save(model);
		return "toList";
	}
	/**
	 * edit interface
	 * @return
	 */

	public String editUI()throws Exception{
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "editUI";
	}
	/**
	 * edit return to list
	 * @return
	 */
    
	public String edit()throws Exception{
		Role role = roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "toList";
	}
	
	public String setPrivilegeUI()throws Exception{
		
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		List<Privilege>  topPrivilegeList = privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
		
		privilegeIds = new Long[role.getPrivileges().size()];
		int index =0;
		for(Privilege p : role.getPrivileges()){
			privilegeIds[index++]=p.getId();
		}
		
		
		return "setPrivilegeUI";
	}
	
	
	
    
	public String setPrivilege()throws Exception{
		Role role = roleService.getById(model.getId());
		
		List<Privilege> privilegeList=privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		
		roleService.update(role);
		
		return "toList";
	}
	
	
}
