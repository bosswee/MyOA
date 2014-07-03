package com.wee.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wee.oa.base.DaoSupportImpl;
import com.wee.oa.domain.Forum;
import com.wee.oa.service.ForumService;
@Service
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements ForumService {

	@Override
	public void moveUp(Long id) {
		// 通过交换position的值实现板块上移功能
		
		//拿到上下两个forum
		
		Forum forum = getById(id);
		Forum other = (Forum) getSession().createQuery(
				"From Forum f WHERE f.position<? ORDER BY f.position DESC")
				.setParameter(0, forum.getPosition())
				.setFirstResult(0)//从0的位置开始索引
				.setMaxResults(1)//取一个值
				.uniqueResult();
		
		//确定不在最上面
				
		if(other== null){
			return;
		}
		
		//交换position
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		//保存到数据库,因为持久化，这一步可以省略
		getSession().update(forum);
		getSession().update(other);
		
		
	}
	/**
	 * 使得forum按照id自动升值
	 */

	@Override
	public void save(Forum forum) {
		getSession().save(forum);
		
		forum.setPosition(forum.getId().intValue());
		
	}
	/**
	 *取出时按照positionASC取出
	 */

	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("From Forum f ORDER BY f.position ASC").list();
	
		
	}

	@Override
	public void moveDown(Long id) {
		// 通过交换position的值实现板块下移功能
		
				//拿到上下两个forum
				
				Forum forum = getById(id);
				Forum other = (Forum) getSession().createQuery(
						"From Forum f WHERE f.position>? ORDER BY f.position ASC")
						.setParameter(0, forum.getPosition())
						.setFirstResult(0)//从0的位置开始索引
						.setMaxResults(1)//取一个值
						.uniqueResult();
				
				//确定不在最下面
						
				if(other== null){
					return;
				}
				
				//交换position
				int temp = forum.getPosition();
				forum.setPosition(other.getPosition());
				other.setPosition(temp);
				
				//保存到数据库,因为持久化，这一步可以省略
				getSession().update(forum);
				getSession().update(other);
				
		
	}

}
