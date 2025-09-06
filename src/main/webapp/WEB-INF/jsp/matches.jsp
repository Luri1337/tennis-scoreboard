<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="${pageContext.request.contextPath}/images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>

<main>
    <div class="container">
        <h1>Matches</h1>

        <!-- фильтр -->
        <div class="input-container">
            <form method="get" action="${pageContext.request.contextPath}/matches">
                <input class="input-filter"
                       placeholder="Filter by name"
                       type="text"
                       name="filter"
                       value="${filter != null ? filter : ''}" />
                <button type="submit" class="btn-filter">Apply</button>
                <a href="${pageContext.request.contextPath}/matches">
                    <button type="button" class="btn-filter">Reset Filter</button>
                </a>
            </form>
        </div>

        <!-- таблица матчей -->
        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${matches}">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td>
                        <c:choose>
                            <c:when test="${match.winner != null}">
                                <span class="winner-name-td">${match.winner.name}</span>
                            </c:when>
                            <c:otherwise>In Progress</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
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
    </div>
</main>

<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard</p>
    </div>
</footer>
</body>
</html>
