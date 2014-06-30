package com.wee.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wee.oa.domain.Department;

public class DepartmentUtils {



	public static List<Department> getAllDepartmentList(List<Department> topList,Department removedDepartment) {
		List<Department> list = new ArrayList<Department>();
		walktree(topList,"",list,removedDepartment);
		return list;
	}
	
	private static void walktree(Collection<Department> topList,
			String prefix,List<Department> list,Department removedDepartment)  {
		for(Department top : topList){
			
			if(removedDepartment!=null&&top.getId().equals(removedDepartment.getId())){
				continue;
			}
			
		//顶点
			Department copy = new Department();
			copy.setId(top.getId());
		    copy.setName(prefix+"┣"+top.getName());
		    list.add(copy);
		//子树
		    walktree(top.getChildren(),"++++"+prefix,list,removedDepartment);
	}
	}

}