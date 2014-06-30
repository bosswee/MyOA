package com.wee.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.wee.oa.base.DaoSupportImpl;
import com.wee.oa.domain.User;
import com.wee.oa.service.UserService;
@Service
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		//将秘密md5加密
		String md5 = DigestUtils.md5Hex(password);
		
		
		
		return (User) getSession().createQuery(
				
	"FROM User u WHERE u.loginName =? AND u.password =?")
	.setParameter(0,loginName).setParameter(1, md5).uniqueResult();
	}
		
}
