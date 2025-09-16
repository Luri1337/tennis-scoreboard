<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error | Tennis Scoreboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ErrorStyle.css">
</head>
<body>
<div class="error-container">
    <h1 class="error-title">Oops! An <span class="accent">error</span> occurred 🎾</h1>

    <div class="tennis-ball">
        <div class="eyes">
            <div class="eye"></div>
            <div class="eye"></div>
        </div>
        <div class="sad-mouth"></div>
    </div>

    <div class="error-info">
        <p><strong class="error-code">Code:</strong> ${status}</p>
        <p><strong class="error-message">Message:</strong> ${message}</p>
        <p>Even the tennis ball is sad about this...</p>
    </div>

    <a class="back-link" href="${pageContext.request.contextPath}/">Go back home</a>
</div>
</body>
</html>