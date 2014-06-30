package com.wee.oa.service;

import com.wee.oa.base.DaoSupport;
import com.wee.oa.domain.User;

public interface UserService extends DaoSupport<User>{

	User findByLoginNameAndPassword(String loginName, String password);

}
