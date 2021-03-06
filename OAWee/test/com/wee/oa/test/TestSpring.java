package com.wee.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




public class TestSpring {

	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

	// 测试SessionFactory
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf.openSession());
	}

	@Test // 测试声明式事务
	public void testTransaction() {
		TestService  service = (TestService) ac.getBean("testService");
		service.addUsers();
	}
	
	// 测试Action对象的管理
		@Test
		public void testAction() throws Exception {
			TestAction testAction = (TestAction) ac.getBean("testAction");
			System.out.println(testAction);
		}
	}

