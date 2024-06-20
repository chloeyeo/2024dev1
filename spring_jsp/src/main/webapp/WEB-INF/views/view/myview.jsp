<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>this is a JSP file</title>
</head>
<body>
	<h2>안녕하세요, 마이뷰입니다.</h2>
	<div>
		<c:forEach var="mylist" items="${lists}">
			<div>${mylist}</div>
		</c:forEach>
	</div>
</body>
</html>