<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page_nav">
	<!-- 判断页码的起始结束位置 -->
	<c:choose>
		<c:when test="${page.pageNumber < 4}">
			<c:set var="begin" value="1" />
			<c:set var="end" value="5" />
			<c:if test="${end > page.totalPage}">
				<c:set var="end" value="${page.totalPage}" />
			</c:if>
		</c:when>
		<c:otherwise>
			<c:set var="begin" value="${page.pageNumber - 2 }" />
			<c:set var="end" value="${page.pageNumber + 2 }" />
			<c:if test="${end > page.totalPage}">
				<c:set var="end" value="${page.totalPage}" />
				<c:set var="begin" value="${page.totalPage - 4 == 0?1:page.totalPage - 4}" />
			</c:if>
		</c:otherwise>
	</c:choose>				

	<a href="${page.path }&pageNumber=1">首页</a>
	<c:if test="${page.pageNumber!=1}">
		<a href="${page.path }&pageNumber=${page.pageNumber-1}">上一页</a>
	</c:if>
	<c:forEach begin="${begin }" end="${end}" var="i">
		<c:choose>
			<c:when test="${page.pageNumber==i }">
				<a>【${i}】</a>
			</c:when>
			<c:otherwise>
				<a href="${page.path }&pageNumber=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${page.pageNumber!=page.totalPage}">
		<a href="${page.path }&pageNumber=${page.pageNumber+1}">下一页</a>
	</c:if>
	<a href="${page.path }&pageNumber=${page.totalPage}">末页</a>
	共${page.totalPage}页，${page.totalRecord}条记录 到第<input value="${page.pageNumber}" name="pn" id="pn_input"/>页
	<input type="button" value="确定" id="pg_btn">
	<script type="text/javascript">
		$(function(){
			//获取按钮对象，并绑定单击响应函数
			$("#pg_btn").click(function(){
				//获取页码
				var pageNumber = $("#pn_input").val();
				//根据不同页码跳转页面
				//通过修改window.location的属性，可以跳转当前页面
				window.location = "${page.path }&pageNumber="+pageNumber;
			});
			
		});
	</script>
</div>