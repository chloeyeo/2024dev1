<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%@ page language="java"
contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Write</title>
  </head>
  <body>
    <!-- 'write' must be in a form tag -->
    <!-- form: if method is get all params are shown in url
      if method is post the params are not shown in url -->
    <!-- action="write" means submit this form values to "/write" -->
    <form action="write" method="post">
      <table width="400" cellpadding="0" cellspacing="0" border="1">
        <tr>
          <td>Writer</td>
          <td><input type="text" name="writer" /></td>
        </tr>
        <tr>
          <td>Title</td>
          <td><input type="text" name="title" /></td>
        </tr>
        <tr>
          <td>Content</td>
          <td><input type="text" name="content" /></td>
        </tr>
        <input type="submit" name="Submit" />
      </table>
    </form>
    <a href="list">GO BACK TO LIST</a>
  </body>
</html>
