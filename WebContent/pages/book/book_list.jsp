<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		
		$(".add2Cart").click(function(){
			
			var url = '${pageContext.request.contextPath}/client/CartServlet';
			var queryStr = {method:"addBook2Cart",bookId:this.id};
			var bookName = $(this).parents(".book_info").find(".book_name .sp2").html();
			$.get(url,queryStr,function(data){
				$("#cart_info").html("<span>您的购物车中有 <span style='color: red'>"+data+"</span> 件商品</span> <div>您刚刚将<span style='color: red'>"+bookName+"</span>加入到了购物车中</div>");
			},"text");
			
			
		});
	});

</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@include file="/WEB-INF/include/user_info.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet" method="get">
					<input type="hidden" value="findBookPageByPrice" name="method"/>
					价格：<input type="text" name="min" value="${param.min}"> 元 - <input type="text" name="max" value="${param.max}" /> 元
					<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center" id="cart_info">
				<c:choose>
					<c:when test="${empty cart || cart.totalCount<1}">
						<span>您的购物车中<span style="color: red">没有</span>商品</span>
					</c:when>
					<c:otherwise>
						<span>您的购物车中有 <span style="color: red">${cart.totalCount }</span> 件商品</span>
					</c:otherwise>
				</c:choose>
				<div>
					<c:if test="${!empty title }">
						您刚刚将<span style="color: red"> ${title } </span>加入到了购物车中
						<c:remove var="title" scope="session"/>
					</c:if>
				</div>
			</div>
			
			<c:choose>
				<c:when test="${empty page.data }">
					<h1>没有发现符合要求的图书</h1>
				</c:when>
				<c:otherwise>
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="${pageContext.request.contextPath}/${book.imgPath }" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span>
									<span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">￥${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<c:if test="${book.stock > 0 }">
										<button class="add2Cart" id="${book.id }">加入购物车</button>
									</c:if>
									<c:if test="${book.stock <= 0 }">
										<button disabled="disabled">加入购物车</button>
									</c:if>
									
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		
		<%@include file="/WEB-INF/include/page.jsp" %>
		
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>