package com.wee.oa.service;



import java.util.List;

import com.wee.oa.base.DaoSupport;
import com.wee.oa.domain.Privilege;


public interface PrivilegeService extends DaoSupport<Privilege>{

	List<Privilege> findTopList();
       
	
	List<String> getAllPrivilegeUrls();
	
	
}
