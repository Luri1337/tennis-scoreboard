<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New Match | Tennis Scoreboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/NewMatchStyle.css">
</head>
<body>
<header class="site-header">
    <div class="container header-inner">
        <div class="brand">
            <div class="logo-circle">TS</div>
            <div class="brand-text">
                <span class="title">TennisScoreboard</span>
                <span class="subtitle">Live match scoreboard</span>
            </div>
        </div>
        <nav class="nav">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/matches?page=1&filter=">Matches</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/new-match">New match</a>
        </nav>
    </div>
</header>

<main class="container main-content">
    <div class="form-container">
        <h1 class="form-title">Create <span class="accent">New Match</span></h1>
        <form action="${pageContext.request.contextPath}/new-match" method="post">
            <div class="form-group">
                <label class="form-label" for="player1">Player 1 Name</label>
                <input class="form-input" type="text" id="player1" name="player1" required placeholder="Enter first player name">
            </div>
            <div class="form-group">
                <label class="form-label" for="player2">Player 2 Name</label>
                <input class="form-input" type="text" id="player2" name="player2" required placeholder="Enter second player name">
            </div>
            <div class="btn-container">
                <button class="btn btn-primary" type="submit">Start Match</button>
                <a class="btn btn-outline" href="${pageContext.request.contextPath}/">Cancel</a>
            </div>
        </form>
    </div>
</main>
</body>
</html>