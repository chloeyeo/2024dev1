<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%@ page language="java"
contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>DTO,DAO</title>
  </head>
  <body>
    <h1>DTO fields are columns in DB</h1>
    <c:forEach var="dto" items="${lists}">
      <div>ID of DTO: ${dto.id}</div>
      <div>Title of DTO: ${dto.title}</div>
    </c:forEach>
  </body>
</html>
