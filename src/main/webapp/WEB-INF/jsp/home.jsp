<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Home</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/HomeStyle.css" />
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
    <section class="hero">
        <h1 class="hero-title">Welcome to <span class="accent">Tennis Scoreboard</span>!</h1>
        <p class="hero-sub">Create a new match, track points and review finished matches.</p>

        <div class="hero-actions">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/new-match">Start New Match</a>
            <a class="btn btn-outline" href="${pageContext.request.contextPath}/matches?page=1&filter=">See Finished Matches</a>
        </div>
    </section>

    <section class="info-cards">
        <div class="card">
            <h3>Easy to use</h3>
            <p>Create matches in seconds and control the score with single-click buttons.</p>
        </div>
        <div class="card">
            <h3>Rules built-in</h3>
            <p>Supports tennis scoring rules, tie-breaks and best-of-3 sets.</p>
        </div>
    </section>
</main>
</body>
</html>
