<%@ taglib uri="jakarta.tags.core" prefix="c" %> <%@ page language="java"
contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Ticket</title>
  </head>
  <body>
    <h2>Ticket</h2>
    <form action="./buy_ticket_card">
        Customer: <input type="text" name="consumerId"><br>
        Amount: <input type="text" name="amount"><br>
        Error: <input type="text" name="error" value="0"><br>
        <input type="submit" value="Buy Ticket" />
    </form>
  </body>
</html>
