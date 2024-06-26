<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%@ page language="java"
contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Mydb View by Id</title>
  </head>
  <body>
    <!-- in MyController.java we put model.addAttribute("dto", dao.viewDao(sId));
     so the attribute was "dto" so we get "dto".id and dto.title -->
    <h1>view dao -> view single dto by id</h1>
    <div>ID of DTO: ${dto.id}</div>
    <div>Title of DTO: ${dto.title}</div>
    <a href="./list">LIST</a>
  </body>
</html>
