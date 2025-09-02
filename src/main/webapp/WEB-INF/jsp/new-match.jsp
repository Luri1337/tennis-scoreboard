<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 02.09.2025
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Match</title>

</head>
<body>
    <h2> Create new match</h2>
    <form action="${pageContext.request.contextPath}/new-match" method="post">
        <label> Player 1: <input type= "text" name = "player 1" required> </label> <br><br>
        <label> Player 2: <input type= "text" name = "player 2" required> </label> <br><br>
        <button type="submit"> Start Match </button>
    </form>
</body>
</html>
