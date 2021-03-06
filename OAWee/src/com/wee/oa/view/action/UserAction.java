package com.wee.oa.view.action;

import java.util.HashSet;
import java.util.List;






import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wee.oa.base.BaseAction;
import com.wee.oa.domain.Department;
import com.wee.oa.domain.Reply;
import com.wee.oa.domain.Role;
import com.wee.oa.domain.User;
import com.wee.oa.util.DepartmentUtils;
import com.wee.oa.util.QueryHelper;




@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {	

	private Long departmentId;
	private Long[] roleIds;

	/** 列表 */
	public String list() throws Exception {
		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);
		
		// 准备分页的数据 v4 （最终版）-- 使用QueryHelper
		new QueryHelper(User.class, "u")
				.preparePageBean(userService, currentPage);

		
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

//	/** 添加 */
	public String add() throws Exception {
		// 封装对象
		// >> 处理关联的一个部门
		model.setDepartment(departmentService.getById(departmentId));
		// >> 处理关联的多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));

		// 保存到数据库
		userService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		// >> 处理部门
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		// >> 处理岗位
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for (Role role : user.getRoles()) {
			roleIds[index++] = role.getId();
		}

		// 准备数据：departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartmentList(topList, null);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 处理关联的一个部门
		user.setDepartment(departmentService.getById(departmentId));
		// >> 处理关联的多个岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}

	/** 初始化密码为1234 */
	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		
		String md5 = DigestUtils.md5Hex("1234"); // 密码要使用MD5摘要
		user.setPassword(md5);

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}

	// ---

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}