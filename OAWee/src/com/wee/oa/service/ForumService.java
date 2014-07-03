package com.wee.oa.service;

import com.wee.oa.base.DaoSupport;
import com.wee.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	void moveUp(Long id);

	void moveDown(Long id);
	
	

}
