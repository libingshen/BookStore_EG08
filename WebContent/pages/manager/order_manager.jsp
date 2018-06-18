<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%@include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@include file="/WEB-INF/include/manager_info.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty orders }">
				<h1>没有订单！</h1>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>日期</td>
						<td>金额</td>
						<td>详情</td>
						<td>发货</td>
						
					</tr>		
					<c:forEach items="${orders }" var="order">
						<tr>
							<td><fmt:formatDate value="${order.date }" pattern="yyyy.MM.dd hh:mm:ss"/></td>
							<td>${order.amount }</td>
							<td><a href="#">查看详情</a></td>
							<c:choose>
								<c:when test="${order.state==0 }">
									<td><a href="manager/OrderManagerServlet?method=deliver&orderId=${order.id }">点击发货</a></td>
								</c:when>
								<c:when test="${order.state==1 }">
									<td><span style="color:red">等待收货</span></td>
								</c:when>
								<c:otherwise>
									<td><span style="color:green">交易完成</span></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		
		</c:choose>
		
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>