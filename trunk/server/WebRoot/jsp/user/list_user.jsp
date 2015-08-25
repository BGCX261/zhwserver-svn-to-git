<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/tags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>用户列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="<c:url value='/css/application.css'/>">
		<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
		<script type="text/javascript"
			src="<c:url value="/js/jquery-1.4.2.min.js"/>"></script>
		<style>
			.errorMessage {
				color: red;
			}
			
			.link,.link_span {
				color: blue;
				cursor: hand;
				text-decoration: underline;
			}
       </style>


	</head>


	<body>
		<div id="list_title">
			&gt;&gt; 用户信息
			<span id="switch_qry" onclick="switchQueryArea();"> [隐藏查询选择] </span>
		</div>
		<div id="query_area">
			<form action="<%=request.getContextPath() %>/user/list.do">
		    用户编码：<input type="text" class="text" name="userNo" value="${queryUser.userNo}"/>
             用户名称：<input type="text" class="text" name="userName"  value="${queryUser.userName}"/>
             是否在职：
             <select name="userStatus" class="text">
             	<option value="">请选择</option>
             	<option value="1" <c:if test="${queryUser.userStatus=='1'}">selected</c:if>>在职</option>
             	<option value="0" <c:if test="${queryUser.userStatus=='0'}">selected</c:if>>离职</option>
             </select>
			 <input type="submit" value=" 查 询 "/>
			 <input type="hidden" name="pageNo" value="${page.pageNo}"/>
			</form>
			
		</div>
		<c:if test="${page.content!='null'}">
			<table class="listable" cellspacing="0" cellpadding="0" border="0">
				<tr bgColor="blue">
					<th class="scol">
						序  号
					</th>
					<th class="scol">
						用户编码
					</th>
					<th class="scol">
						用户名称
					</th>
					<th class="scol">
						是否在职
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="user" items="${page.content}">
					<tr>
						<td>
							${user.userId} 
						</td>
						<td>
							${user.userNo} 
						</td>
						<td>
							${user.userName} 
						</td>
						<td>
								<c:if test="${user.userStatus == '1'}">在职</c:if> 
								<c:if test="${user.userStatus == '0'}">离职</c:if>
						</td>
						<td>
							<a href="<%=request.getContextPath()%>/user/${user.userId}.do">修改</a>

							<span class="link" title="删除用户"
								onclick="remove('${user.userName} ','${user.userId}');">删除
							</span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<hr>

			<div id="page_nav">
				共
				<span class="page_text">${page.totalRecNum}</span>条&nbsp; 每页
				<span class="page_text">${page.pageSize}</span>条&nbsp; 当前列出 第
				<span class="page_text">${page.startIndex}</span>条 至 第
				<span class="page_text">${page.endIndex}</span>条&nbsp; 第
				<span class="page_text">${page.pageNo}/${page.totalPageNum}</span>页


				&nbsp;|&nbsp;
				<c:if test="${page.prePage!='null'}">
					<span class="link_span" onclick="gotoPage(1);" title="访问首页">首页</span>
				</c:if>
				<c:if test="${page.prePage!='null'}">
					<span class="link_span" onclick="gotoPage(${page.pageNo-1});"
						title="访问第${page.pageNo-1}页">上一页</span>
				</c:if>
				<c:if test="${page.nextPage!='null'}">
					<span class="link_span" onclick="gotoPage(${page.pageNo+1});"
						title="访问第${page.pageNo+1}页">下一页</span>
				</c:if>
				<c:if test="${page.nextPage!='null'}">
					<span class="link_span" onclick="gotoPage(${page.totalPageNum});"
						title="访问末页">末页</span>
				</c:if>

				&nbsp;|&nbsp; 跳转到
				<input type="text" id="pageNo" value="${page.pageNo}" class="text" size="2"
					style="text-align: right;" onKeyPress="onlyNumber();" />
				页&nbsp;
				<button onclick="gotoPage($('#pageNo').val());"
					style="height: 19px;">
					跳 转
				</button>

			</div>

		</c:if>
		<c:if test="${page.content=='null'}">
			<center>
				<div id="no_rec">
					<span class="failMsg">没有符合条件的帐户记录被找到！</span>
					<button onclick="history.go(-1);">
						返 回
					</button>
				</div>
			</center>
		</c:if>

	</body>
	<script type="text/javascript">
	    var cols=new Array("userId","userNo","userName","userStatus");
	    $(document).ready(           
            function()
            {
               var rIndexCol = $("#index").val();
               //alert(rIndexCol);
               var rtype = $("#colType").val();
              // alert(rtype); 
               if(rtype=="asc")
               {
                 $("th.scol").eq(parseInt(rIndexCol))
                       .append("<img style='margin-left:5px;' src='<c:url value="/pics/side_arrow_up.gif"/>'/>");
               }
               else
                 $("th.scol").eq(parseInt(rIndexCol))
                       .append("<img style='margin-left:5px;' src='<c:url value="/pics/side_arrow_down.gif"/>'/>");
               
               
               $("th.scol").css("cursor","hand")
                      .bind(
                           "mouseover",
                           function(){
                              $(this).css("text-decoration","underline");
                           }
                      )
                     .bind(
                           "mouseout",
                           function(){
                              $(this).css("text-decoration","");
                           }
                      )
                      .bind(
                            "click",
                            function(){
                                var colIndexTemp=$("th.scol").index($(this));
                                var colNameTemp=cols[$("th.scol").index($(this))];
                                var sortedTypeTemp="";
                                if(parseInt(rIndexCol)!= $("th.scol").index($(this)))
                                   sortedTypeTemp="asc";
                                else
                                   sortedTypeTemp=rtype=="asc"?"desc":"asc"
                                
                                //alert(colIndexTemp+"|"+colNameTemp+"|"+sortedTypeTemp);                              
                                $("#index").attr('value',colIndexTemp);
                                $("#colName").attr('value',colNameTemp);
                                $("#colType").attr('value',sortedTypeTemp);
                                $("input[name='pageNo']").attr('value',1);
                                
                                document.forms[0].submit();
                            }
                           );	                                                                                                                            
            }
          
         );
	    function remove(name,id)
        {
          if(confirm("真的要注销用户[" + name + "]吗？"))
             location.href="<%=request.getContextPath() %>/user/delete.do?userId="+id;         
        }


         function switchQueryArea()
         {
            $('#query_area').toggle();
            if($('#query_area').css('display')=='none')
               $('#switch_qry').text(' [显示查询选择] ');
            else
               $('#switch_qry').text(' [隐藏查询选择] ');
         }
        function gotoPage(pageNo)
         {
            if(pageNo<1 || pageNo>${page.totalPageNum})
            {
              alert("页面编号输入错误！范围为1-${page.totalPageNum}");
              $('#pageNo').get(0).select();
              return;
            }
            //alert($("input[name='page.pageNo']").val());
            
            $("input[name='pageNo']").attr('value',pageNo);
            document.forms[0].submit();
         }
         
         function newQuery()
         {
           $("input[name='pageNo']").attr('value','1');
                        return true;
         }
   </script>
</html>
