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
      <div>
        <a href="./view?id=${dto.id}">ID of DTO: ${dto.id}</a>
        <a href="./delete?id=${dto.id}">delete</a>
      </div>
      <div>Writer of DTO: ${dto.writer}</div>
      <div>Title of DTO: ${dto.title}</div>
      <div>Content of DTO: ${dto.content}</div>
    </c:forEach>
    <!-- we already have a '/write' so should use a different name - writer form -->
    <a href="./writerForm">Write</a>
  </body>
</html>
