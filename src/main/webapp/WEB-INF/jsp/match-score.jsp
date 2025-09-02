<%--
  Created by IntelliJ IDEA.
  User: Dima
  Date: 02.09.2025
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match Score</title>
</head>
<body>
    <h2> Match Score </h2>

    <table border="1" cellpadding="5">
        <tr>
            <th>Игрок</th>
            <th>Счёт</th>
        </tr>
        <tr>
            <td>${match.player1Name}</td>
            <td>${match.player1Score}</td>
        </tr>
        <tr>
            <td>${match.player2Name}</td>
            <td>${match.player2Score}</td>
        </tr>
    </table>

    <br>

</body>
</html>
