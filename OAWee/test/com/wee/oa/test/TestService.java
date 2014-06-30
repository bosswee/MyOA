package com.wee.oa.test;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wee.oa.domain.User;
@Service
public class TestService {
	
	
		@Resource
		private SessionFactory sessionFactory;
		@Transactional
		public void addUsers() {
			sessionFactory.getCurrentSession().save(new User());
			//int a = 1 / 0; // 这行会抛异常
			sessionFactory.getCurrentSession().save(new User());
		}
	
	
}
