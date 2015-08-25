<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/tags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修改用户信息</title>
		<%@ include file="/jsp/common/header.jsp"%>
	</head>
	<body>
		<form action="<%=request.getContextPath() %>/user/modify.do">
			<div id="entry_header">修改用户信息</div>
			<div id="group_line">用户信息</div>
			<div id="group_entry">
			  <input type="hidden" name="userId" value="${user.userId}"/>
			  <ul style="padding: 5px;">
				<li>
					<span class="form_label">用户编码：</span>
					<span> <input type="text" name="userNo" value="${user.userNo}" size="32"maxlength="30"/> </span>
				</li>
				<li>
					<span class="form_label">用户名称：</span>
					<span> <input type="text" name="userName" value="${user.userName}" size="32"maxlength="30"/> </span>
				</li>
				<li>
					<span class="form_label">是否离职：</span>
					<span> 
						<input type="radio" name="userStatus" <c:if test="${user.userStatus=='1'}">checked</c:if> value="1">在职
						<input type="radio" name="userStatus" <c:if test="${user.userStatus=='0'}">checked</c:if> value="0">离职
					<br/></span>
				</li>
			  </ul>
			</div> 
		    <div id="submitDiv">
		        <input type="submit" value="修改用户信息"/>
	            <input type="reset" value="取消重写"/>
            </div>
		</form>
	</body>
</html>
