<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		
		//为所有.delBook绑定一个单击响应函数
		$(".delA").click(function(){
			
			//获取要删除的书名
			var title = $(this).parents("tr").find("td:eq(0)").html();
			
			//弹出一个确认框
			if(!confirm("确认删除【"+title+"】吗？")){
				
				//取消默认行为
				return false;
			}
			
		});
		
		$(".upload").click(function(){
			var sFeatures = "height=200, width=410, scrollbars=yes, resizable=yes, location=yes";
			window.open( $(this).attr('href'), 'upload',sFeatures );
	        return false;
		});
		
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@include file="/WEB-INF/include/manager_info.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>	
			<c:if test="${empty page.data }">
					<tr><td colspan="7">数据库中没有图书</td></tr>
			</c:if>
			<c:forEach items="${page.data }" var="book">
				<tr>
					<td>${book.title }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<td  colspan="2">
						<a href="manager/BookManagerServlet?method=toEditUI&bookId=${book.id }">修改</a>&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/pages/manager/fileUploadServlet.jsp?bookId=${book.id}" class="upload">上传图片</a>&nbsp;&nbsp;&nbsp;
						<a class="delA" href="manager/BookManagerServlet?method=delBook&bookId=${book.id }">删除</a>
					</td>
				</tr>	
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>
			<%@include file="/WEB-INF/include/page.jsp" %>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>