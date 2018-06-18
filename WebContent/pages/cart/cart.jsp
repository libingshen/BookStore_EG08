<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		
		//为delA绑定单击响应函数
		$(".delA").click(function(){
			
			//获取书名
			var title = $(this).parents("tr").find("td:eq(0)").html();
			
			//弹出确认框
			if(!confirm("你真的要删除《"+title+"》吗")){
				
			}else{
				var url = this.href;
				$(this).parents("tr").remove();
				$.get(url,function(data){
					if(data.count==0){
						$("#main").html("<h1 align='center' style='margin-top:200px'>购物车中还没有商品</h1>");
					}else{
						$("#total_count").html(data.count);
						$("#total_amount").html(data.amount);
					}
				},'json');
			}
			
			//取消默认行为
			return false;
		});
		
		
		//为cou_inp绑定改变的事件
		$(".cou_inp").change(function(){
			
			//获取文本框的内容
			var count = this.value;
			
			//判断当前内容是否为数字
			if(isNaN(count) || count <= 0){
				//修改成原来的值
				this.value = this.defaultValue;
				return;
			}
			
			if(Number(count) > Number(this.name)){
				alert("输入的数量以超过最大库存数，请输入一个小于等于"+this.name+"的数量");
				this.value = this.defaultValue;
				return;
			}
			
			//将bookId作为input的id属性，通过this.id可以获取到当前图书的ID
			//是一个有效的的数量,向Servlet发送请求
			var url = "${pageContext.request.contextPath}/client/CartServlet?method=updateCount&count="+count+"&bookId="+this.id;
			$.get(url,function(data){
				$("#total_count").html(data.count);
				$("#total_amount").html(data.amount);
				$("#item_amount").html(data.item_amount);
			},'json');
			
		});
		
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/WEB-INF/include/user_info.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty cart || empty cart.cartItem}">
				<h1 align="center" style='margin-top:200px'>购物车中还没有商品</h1>
			</c:when>
			 <c:otherwise>
			 		<table>
						<tr>
							<td>商品名称</td>
							<td>数量</td>
							<td>单价</td>
							<td>金额</td>
							<td>操作</td>
						</tr>		
						<c:forEach items="${cart.cartItem}" var="cartItem">
							<tr>
								<td>${cartItem.book.title }</td>
								<td><input name="${cartItem.book.stock }" id="${cartItem.book.id }" class="cou_inp" type="text" value="${cartItem.count }" style="width: 20px;text-align: center;" /> </td>
								<td>${cartItem.book.price }</td>
								<td id="item_amount">${cartItem.amount }</td>
								<td><a class="delA" href="client/CartServlet?method=delCartItem&bookId=${cartItem.book.id}">删除</a></td>
							</tr>
						</c:forEach>
					</table>
					
					<div class="cart_info">
						<span class="cart_span">购物车中共有<span class="b_count" id="total_count">${cart.totalCount }</span>件商品</span>
						<span class="cart_span">总金额<span class="b_price" id="total_amount">${cart.totalAmount }</span>元</span>
						<span class="cart_span"><a href="client/CartServlet?method=clearCart">清空购物车</a></span>
						<span class="cart_span"><a href="client/OrderClientServlet?method=createOrder">去结账</a></span>
					</div>
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