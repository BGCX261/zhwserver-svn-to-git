<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/tags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
	</head>
<style>
BODY {
	FONT-SIZE: 12px; BACKGROUND: #ced7f7; SCROLLBAR-HIGHLIGHT-COLOR: buttonface; SCROLLBAR-SHADOW-COLOR: buttonface; SCROLLBAR-3DLIGHT-COLOR: buttonhighlight; SCROLLBAR-TRACK-COLOR: #eeeeee; FONT-FAMILY: "宋体", "Verdana", "Arial"; SCROLLBAR-DARKSHADOW-COLOR: buttonshadow
}
TD {
	FONT-SIZE: 12px
}
INPUT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
TEXTAREA {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SELECT {
	BORDER-TOP-WIDTH: 1px; BORDER-LEFT-WIDTH: 1px; FONT-SIZE: 12px; BORDER-BOTTOM-WIDTH: 1px; BORDER-RIGHT-WIDTH: 1px
}
SPAN {
	FONT-SIZE: 12px; POSITION: static
}
.button {
	FONT: bold 11px tahoma,verdana,arial,helvetica,sans-serif
}
.s01 {
	BACKGROUND-COLOR: #F3F3F3;
	border: 1px solid #6685C5;
}
.s02 {
	BACKGROUND-COLOR: #F3F3F3; BORDER-BOTTOM: #000000 1px ridge; BORDER-LEFT: #000000 1px ridge; BORDER-RIGHT: #000000 1px ridge; BORDER-TOP: #000000 1px ridge
}
</style>
<script>
function checkinput(theForm)
{
   	  if (theForm.username.value == "")
	  {
		    alert("请输入用户名");
		    loginForm.username.focus();
		    return (false);
	  }
	
	  if (theForm.password.value == "")
	  {
		    alert("请输入密码.");
		    loginForm.password.focus();
		    return (false);
	  }
	  
	  if (theForm.rand.value == "")
	  {
		    alert("请输入验证码.");
		    loginForm.rand.focus();
		    return (false);
	  }
	  return true;
}
</script>
	<body>
<p>&nbsp;</p>
<form method="post" name="loginForm" action="<%=request.getContextPath() %>/admin/login.do" onsubmit="return checkinput(this);">
<table width="539" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#6685C5">
  <tr>
    <td bgcolor="#FFFFFF">
	    <table width="539" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td colspan="3"><img src="<c:url value="/pics/yangmi.jpg"/>" width="539" height="349"></td>
	      </tr>
	      <tr>
	        <td height="35" width="40%" align="right">
	        	<div align="right">
	        		用户：<INPUT name="username" class="s01" size=16 maxLength=16>&nbsp;&nbsp;
	       		</div>
	       	</td>
	       	<td width="30%">
	       		密码：<INPUT name="password" type="password" class="s01" size=16 maxLength=20><br>
	       	</td>
	       	<td>
	       		&nbsp;<span style="color:red">${errormsg}</span> 
	       	</td>
	      </tr>
	      <tr>
	        <td height="35">
	        	<div align="right">
	       			验证码：<input type="text" name="rand" class="s01" size=16 maxLength=16/>&nbsp;&nbsp;
	       		</div>
	       	</td>
	       	<td colspan="2">
	       		<img src="<%=request.getContextPath()%>/authImg" id="authImg"/>&nbsp;看不清？<a href="#" onClick="refresh()" class="linkmain">单击此处刷新</a>
	       	</td>
	      </tr>
	      <tr>
	        <td height="37" colspan="2">
	        	<div align="center">
	        		版权所有：具体是什么版权我能告诉你？&nbsp;
	        	</div>
	        </td>
	        <td>
	        	<input name="submit" type="submit" class="s02" value="登 陆">
	          	<input name="submit1" type="reset" class="s02" id="submit1" value="取 消">
	        </td>
	      </tr>
	    </table>
    </td>
  </tr>
</table> 
</form>
</body>
<script>
	function refresh()
	{
	 	document.getElementById("authImg").src='<%=request.getContextPath()%>/authImg?now=' + new Date();
	}
</script>
</html>
