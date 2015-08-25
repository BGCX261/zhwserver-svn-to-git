<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/tags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>创建用户</title>
		<%@ include file="/jsp/common/header.jsp"%>

		<script type="text/javascript">
		   function validate()
		   {
		      if($("input[name = 'user.userNo']").val() == "")
		      {
		          alert("用户编号不能为空！");
		          return false;
		      }
		         
		         
		      if($("input[name = 'user.userName']").val() == "")
		      {
		          alert("用户名称不能为空！");
		          return false;
		      }
		      return true;
		   }
		   
        </script>
	</head>

	<body>
		<form action="<%=request.getContextPath() %>/user/add.do" onsubmit="return validate();">
			<div id="entry_header">创建用户</div>
			<div id="group_line">用户信息</div>
			<div id="group_entry">
			  <ul style="padding: 5px;">
				<li>
					<span class="form_label">用户编码：</span>
					<span> <input type="text" name="userNo" size="32" maxlength="30"/> </span>
				</li>
				<li>
					<span class="form_label">用户名称：</span>
					<span> <input type="text" name="userName" size="32"maxlength="30"/> </span>
				</li>
				<li>
					<span class="form_label">是否离职：</span>
					<span> 
					<input type="radio" name="userStatus" value="1">在职
					<input type="radio" name="userStatus" value="0">离职
					</span>
				</li>

			  </ul>
			</div> 
		    <div id="submitDiv">
	            <input type="submit" value="创建用户"/>
	            <input type="reset" value="取消重写"/>
            </div>
		</form>
	</body>
</html>
