<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
	
		
		<c:choose>
			<c:when test="${empty orders}">
				<h1>您目前还没有订单</h1>
			</c:when>	
			<c:otherwise>
				<table style="width: 90% ;">
					<c:forEach items="${orders}" var="order">
						<tr>
							<td>订单号</td>
							<td>日期</td>
							<td>金额</td>
							<td>状态</td>
						</tr>	
						<tr style="background-color: #bfa">
							<td>${order.id }</td>
							<td>
							<fmt:formatDate value="${order.date }" pattern="yyyy-MM-dd hh:mm:ss"/>
							</td>
							<td>${order.amount }</td>
							<td>
								<c:choose>
									<c:when test="${order.state==0 }">
										<span style="color:red">
										未发货
										</span>
									</c:when>
									<c:when test="${order.state==1 }">
										<span style="color:yellow">已发货</span><br />
										 <a style="font-size: 16px" href="client/OrderClientServlet?method=take&orderId=${order.id}">确认收货</a>
									</c:when>
									<c:otherwise>
										<span style="color:green">交易结束</span>
									</c:otherwise>
								</c:choose>
							
							</td>
						</tr>
						<tr>
						
							<td colspan="4">
								<table style="margin: 0 auto auto;">
									<tr>
										<td>图片</td>
										<td>书名</td>
										<td>单价</td>
										<td>数量</td>
										<td>金额</td>
									</tr>
								<c:forEach items="${order.orderItems }" var="item">
									<tr>
										<td><img class="book_img" alt="" src="${pageContext.request.contextPath}${item.book.imgPath}" /></td>
										<td>${item.book.title}</td>
										<td>${item.book.price}</td>
										<td>${item.count }</td>
										<td>${item.amount }</td>
									</tr>	
								</c:forEach>
								</table>
							</td>
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