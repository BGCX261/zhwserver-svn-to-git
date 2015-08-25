<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/tags.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/tree.css"/>">
<script type="text/javascript" src="<c:url value="/js/tree.js"/>"></script>
<html>
  <head>
    <title>用户信息管理</title>
    <%@ include file="/jsp/common/header.jsp" %>
    
    <script type="text/javascript">        
         
         /*
          * 实现左侧导航菜单条的显示切换
          *   arrowImage  黄色箭头对象
          */
         function switchLeftMenuBar(arrowImage)
         {
            //1. 隐藏或者显示菜单条
            $('#navigator').toggle();
            
            var status=$('#navigator').css('display');
            if(status=="none")
            {
               arrowImage.title="显示左侧菜单";
               arrowImage.src="<c:url value="/pics/side_arrow_right.gif"/>";
            }
            else if(status=="block")
            {
               arrowImage.title="隐藏左侧菜单";
               arrowImage.src="<c:url value="/pics/side_arrow_left.gif"/>";               
            }                   
         }  
         
         /*
          * 退出
          */
         function logout() {
        	 document.logoutform.submit();
         }
    </script>
    
  </head>
  
  <body>
    <div id="heading"> <!-- 页面头部 -->
      <div id="heading_logo"></div>
      <div id="info_area">
         <div id="sys_time"></div>
         <div id="userinfo">
            <h2>用户信息管理</h2>
         </div>
      </div>
      <div id="heading_bg"></div>
    </div>
    <form method="post" name="logoutform" action="<%=request.getContextPath() %>/admin/logout.do"></form>
    <div id="pageBody"> <!-- style="border:1px solid green;">  页面体部 -->
      <div id="navigator" style="display:block;"> 
         <div class="tree">
                       &nbsp;&nbsp;欢迎您 ：<%=session.getAttribute("username") %>  
                       &nbsp;&nbsp; <a href="javascript:;" onclick="logout();">退出 </a>
         <br>
				<script type="text/javascript">
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
         
         
      </div>
      <div id="seprator"> <!--onclick="hide('heading');">  -->
        <img src="<c:url value="/pics/sperator-bar.jpg"/>" width="14" height="98"/>
        <br/>
        <br/>
        <img title="隐藏左侧菜单"
             onclick="switchLeftMenuBar(this);" 
             id="hiddenArrow" src="<c:url value="/pics/side_arrow_left.gif"/>"/>
      </div>
      
      <div id="content">
         <iframe name="contentFrame" id="contentFrame" src="" scrolling="no" frameborder="0" width="100%">
              您的浏览器不支持iframe
         </iframe>
      </div>
    </div>   
    
    <%@ include file="/jsp/common/footer.jsp" %>
    <script type="text/javascript">      
       window.setInterval("showTime($('#sys_time')[0]);",1000);
    </script>
  </body>
</html>
