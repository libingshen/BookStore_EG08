<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	<div style="margin-left:70px ; margin-top: 70px ; ">
		<form action="manager/FileUploadServlet" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bookId" value="${param.bookId }" />
			请选择图片:<input type="file" name="img" />
			<input type="submit" value="提交" />
		</form>
	</div>
</body>
</html>