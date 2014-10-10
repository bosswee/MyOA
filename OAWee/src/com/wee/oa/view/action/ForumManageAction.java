package com.wee.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wee.oa.base.ModelDrivenAction;
import com.wee.oa.domain.Forum;

@Controller
@Scope("prototype")
public class ForumManageAction extends ModelDrivenAction<Forum> {

	public String list() throws Exception {
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);

		return "list";
	}

	public String delete() throws Exception {
		forumService.delete(model.getId());
		return "toList";
	}

	public String addUI() throws Exception {
		return "saveUI";
	}

	public String add() throws Exception {
		forumService.save(model);
		return "toList";
	}

	public String editUI() throws Exception {
		// 从数据库取
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);

		return "saveUI";
	}

	public String edit() throws Exception {
		// 从数据库取
		Forum forum = forumService.getById(model.getId());

		// set
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());

		// save

		forumService.save(forum);

		return "toList";
	}

	public String moveUp() throws Exception {
		forumService.moveUp(model.getId());
		return "toList";
	}

	public String moveDown() throws Exception {
		forumService.moveDown(model.getId());
		return "toList";
	}

}
