<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%@ page language="java"
contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>LIST</title>
  </head>
  <body>
    <h1>List of DTOs = DAO</h1>
    <c:forEach var="dto" items="${lists}">
      <a href="/view?id=${dto.id}"><div>ID of DTO: ${dto.id}</div></a>
      <div>Title of DTO: ${dto.title}</div>
    </c:forEach>
  </body>
</html>
