<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<script type="text/javascript">
	
	$(function(){
		
		$("#um").blur(function(){
			var url = "${pageContext.request.contextPath}/client/UserServlet";
			var queryStr = {method:'checkUserName' , username:this.value};
			$.get(url,queryStr,function(data){
				
				if(data == 0){
					//用户名已经存在
					$(".errorMsg").html("用户名不可用");
					$(".errorMsg").css("color","red");
					$("#sub_btn").attr("disabled",true).hide();
				}else{
					//用户名不存在
					$(".errorMsg").html("用户名可以使用");
					$(".errorMsg").css("color","green");
					$("#sub_btn").attr("disabled",false).show();
				}
				
			});
		});
		
		var count = 0;
		
		//点击图片刷新验证码
		$("#code_img").click(function(){
			
			//修改this的src属性
			//当图片的src属性发生改变，浏览器就会去重新加载图片，通过修改img的src属性来达到一个图片刷新的目的
			$(this).attr("src","code.jpg"+"?"+count++);
			
		});
		
		
		/* var reg = /a/;
		alert(reg.test("vvvvabc")); */
		
		//为注册按钮绑定一个单击响应函数
		$("#sub_btn").click(function(){
			
			//获取用户输入的用户名，密码，确认密码，电子邮件，验证码
			var username = $.trim($("#um").val());
			var pwd = $.trim($("#pwd").val());
			var repwd = $.trim($("#repwd").val());
			var email = $.trim($("#email").val());
			var code = $.trim($("#code").val());
			
			//将去空格后的内容回写到网页中
			$("#um").val(username);
			$("#pwd").val(pwd);
			$("#repwd").val(repwd);
			$("#email").val(email);
			
			//验证输入是否符合规范
			var nameReg  = /^[A-Za-z0-9_-]{3,16}$/ ;
			if(!nameReg.test(username)){
				//当用户名不符合规范是弹出提示框
				alert("请输入3-16位，包含字母、数字、_、-的用户名！");
				//取消默认行为
				return false;
			}
			
			//密码的验证
			var pwdReg = /^[a-z0-9_-]{6,18}$/;
			if(!pwdReg.test(pwd)){
				alert("请输入6-18位，包含 小写字母、数字、_、-的密码！");
				return false;
			}
			
			//确认密码
			if(repwd != pwd){
				alert("两次输入的密码不一致！");
				return false;
			}
			
			//验证电子邮件
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("请输入正确电子邮件!");
				return false;
			}

			//验证验证码是不是空的
			if(code == ""){
				alert("请输入验证码！");
				return false;
			}
			
		});
		
		
	});

</script>


</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									<%-- <%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %> --%>
									${msg }
								</span>
							</div>
							<div class="form">
								<form action="client/UserServlet?method=regist" method="post">
									<label>用户名称：</label>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="um" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="pwd" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="userCode"/>
									<img id="code_img" alt="" src="code.jpg" style="float: right; margin-right: 40px; width: 90px;height: 40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>