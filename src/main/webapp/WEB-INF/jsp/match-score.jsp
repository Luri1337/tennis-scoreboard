<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/MatchScoreStyle.css">
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
    <h1 class="page-title">Current <span class="accent">Match</span></h1>

    <div class="match-image">
        <div class="match-image-text">Live Match in Progress</div>
    </div>

    <section class="score">
        <table class="score-table">
            <thead>
            <tr>
                <th>Player</th>
                <th>Sets</th>
                <th>Games</th>
                <th>Points</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="player-name">${match.player1.name}</td>
                <td>${match.firstSets}</td>
                <td>${match.firstGames}</td>
                <td>
                    <c:choose>
                        <c:when test="${match.tieBreak.isTieBreak}">
                            ${match.tieBreak.firstPoints}
                        </c:when>
                        <c:otherwise>
                            ${match.firstPoints.value}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}">
                        <input type="hidden" name="winner" value="${match.player1.id}">
                        <button type="submit" class="score-btn">Score</button>
                    </form>
                </td>
            </tr>

            <tr>
                <td class="player-name">${match.player2.name}</td>
                <td>${match.secondSets}</td>
                <td>${match.secondGames}</td>
                <td>
                    <c:choose>
                        <c:when test="${match.tieBreak.isTieBreak}">
                            ${match.tieBreak.secondPoints}
                        </c:when>
                        <c:otherwise>
                            ${match.secondPoints.value}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}">
                        <input type="hidden" name="winner" value="${match.player2.id}">
                        <button type="submit" class="score-btn">Score</button>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </section>
</main>
</body>
</html>