<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<%@include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@include file="/WEB-INF/include/user_info.jsp" %>
	</div>
	
	<div id="main">
		
		
				<table>
					<tr>
						<td>订单号</td>
						<td>图片</td>
						<td>书名</td>
						<td>数量</td>
						<td>金额</td>
					</tr>
					
					<c:forEach items="${orderItems }" var="item">
					
						<tr>
							<td>${item.orderId }</td>
							<td><img class="book_img" alt="" src="${pageContext.request.contextPath}/static/img/default.jpg" /></td>
							<td>时间简史</td>
							<td>${item.count }</td>
							<td>${item.amount }</td>
						</tr>	
					
					</c:forEach>
				</table>
			
		
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>