package com.wee.oa.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;








import com.wee.oa.base.DaoSupportImpl;
import com.wee.oa.domain.Privilege;
import com.wee.oa.domain.Role;
import com.wee.oa.service.PrivilegeService;
import com.wee.oa.service.RoleService;

@Service
//@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findTopList() {
		
		return getSession().createQuery(
				
				"FROM Privilege p WHERE p.parent IS NULL").list();

	

	}
	

	@Override
	public List<String> getAllPrivilegeUrls() {
		
		return getSession().createQuery(
				
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL"
				).list();
	}
	
}
	 

