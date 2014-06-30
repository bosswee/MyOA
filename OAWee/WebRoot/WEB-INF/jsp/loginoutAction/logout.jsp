<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>您已退出Itcast OA系统</title>
	  <%@ include file="/WEB-INF/jsp/public/header.jspf" %>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />

<script type="text/javascript">
</script>


	<link href="${pageContext.request.contextPath}/style/blue/logout.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<table border=0 cellspacing=0 cellpadding=0 width=100% height=100%>
		<tr>
			<td align=center>
				<div id=Logout>
					<div id=AwokeMsg><img id=LogoutImg src="${pageContext.request.contextPath}/style/blue/images/logout/logout.gif" border=0 /><img id=LogoutTitle src="${pageContext.request.contextPath}/style/blue/images/logout/logout1.gif" border=0 /></div>
					<div id=LogoutOperate>
						<img src="${pageContext.request.contextPath}/style/blue/images/logout/logout2.gif" border=0 /> 
						
						<%--这个超链接不改为标签 --%>
						<a href="${pageContext.request.contextPath}/loginout_loginUI.do">重新进入系统</a>

						<!--
			window.close(); 可以关闭IE6~9
			window.open('', '_self'); window.close(); 可以半闭 chrome
			FireFox不可以关闭
						-->
                    <img src="${pageContext.request.contextPath}/style/blue/images/logout/logout3.gif" border=0 /> <a href="javascript:window.open('', '_self'); window.close();">关闭当前窗口</a>
                    </div>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
	