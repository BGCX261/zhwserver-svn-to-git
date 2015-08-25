<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/tags.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/tree.css"/>">
<script type="text/javascript" src="<c:url value="/js/tree.js"/>"></script>
<div class="tree">
<script type="text/javascript">
		<!--
		d = new dTree('d'); 
		d.add(0,-1,'功能菜单');
		<% 
		String str = (String)request.getAttribute("menustr"); 
		String[] ar =  str.split("@"); 
		for(int i=0;i<ar.length;i++){ 
		%> 
		d.add(<%=ar[i]%>); 
		<%}%> 
        //config配置，设置文件夹不能被链接，即有子节点的不能被链接。
		d.config.folderLinks=false;
        document.write(d); 
</script>
</div>
