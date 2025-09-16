<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/MatchesStyle.css">
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
    <h1 class="page-title">Finished <span class="accent">Matches</span></h1>

    <!-- фильтр -->
    <div class="filter-section">
        <form class="filter-form" method="get" action="${pageContext.request.contextPath}/matches">
            <input class="filter-input"
                   placeholder="Filter by player name"
                   type="text"
                   name="filter"
                   value="${filter != null ? filter : ''}" />
            <input type="hidden" name="page" value="1" />
            <button type="submit" class="filter-btn">Apply Filter</button>
            <a href="${pageContext.request.contextPath}/matches?filter=&page=1" class="reset-btn">Reset Filter</a>
        </form>
    </div>

    <!-- таблица матчей -->
    <table class="matches-table">
        <thead>
        <tr>
            <th>Player One</th>
            <th>Player Two</th>
            <th>Winner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="match" items="${matches}">
            <tr>
                <td>${match.player1.name}</td>
                <td>${match.player2.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${match.winner != null}">
                            <span class="winner-name">${match.winner.name}</span>
                        </c:when>
                        <c:otherwise>
                            <span class="in-progress">In Progress</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- пагинация -->
    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a class="prev"
               href="${pageContext.request.contextPath}/matches?filter=${filter}&page=${currentPage - 1}">
                &lt;
            </a>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <a class="num-page ${i == currentPage ? 'current' : ''}"
               href="${pageContext.request.contextPath}/matches?filter=${filter}&page=${i}">
                    ${i}
            </a>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <a class="next"
               href="${pageContext.request.contextPath}/matches?filter=${filter}&page=${currentPage + 1}">
                &gt;
            </a>
        </c:if>
    </div>
</main>
</body>
</html>