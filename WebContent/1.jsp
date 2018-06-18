<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/include/base.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#btn").click(function(){
			var sFeatures = "height=600, width=810, scrollbars=yes, resizable=yes";
			window.open( jQuery(this).attr('href'), '3km', sFeatures );
	        return false;
		});
	});
</script>
</head>
<body>
	<button id="btn">按钮</button>
</body>
</html>