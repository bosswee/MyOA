<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 
  
  <body>
 
    <s:form action="role_edit">
    <s:hidden name="id"></s:hidden>
			名称：<s:textfield name="name"></s:textfield><br/>
			说明：<s:textarea name="description"></s:textarea><br/>
			<s:submit value="提交"></s:submit>
		</s:form>
 
 
 
  </body>
</html>
