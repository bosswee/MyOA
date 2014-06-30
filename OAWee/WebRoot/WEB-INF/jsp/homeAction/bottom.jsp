<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>




<html>
<head>
    <title>Bottom</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf" %>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/pageCommon.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/PageUtils.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />

<script type="text/javascript">
</script>


	<link type="text/css" rel="stylesheet" href="style/blue/statusbar.css" />
</head>
<body style="margin:0"> 

<div id="StatusBar">
    <div id="Online">
    	在线人员：共 <span class="OnlineUser" id="onlineUserNum"></span> 人
        <span class="OnlineView"><a href="javascript:void(0)">[查看在线名单]</a></span>
    </div>
    <div id="Info">
    	<a href="http://zhouchaowei.com" title = "wee首页" target="_blank">wee首页</a> |
        <a href="http://zhouchaowei.com" title = "weeBBS" target="_blank">weeBBS</a> 
    </div>
    <div id="DesktopText">
        <a href="javascript:void(0)"><img border="0" src="style/images/top/text.gif"/>便笺</a>
        <span id=TryoutInfo></span>
        <span id="Version">
            <a href="javascript:void(0)">
            	<img border="0" width="11" height="11" src="style/images/top/help.gif" /> 
                <img border="0" width="40" height="11" src="style/blue/images/top/version.gif" />
            </a>
        </span>
    </div>
</div>

</body>
</html>