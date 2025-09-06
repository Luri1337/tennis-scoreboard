package serlvet;

import dao.MatchDao;
import dao.PlayerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.OngoingMatch;
import model.entity.Player;
import org.hibernate.SessionFactory;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private PlayerDao playerDao;

    @Override
    public void init() {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) getServletContext().getAttribute("matchScoreService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesService");
        playerDao = new PlayerDao(sessionFactory);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OngoingMatchesService ongoingMatchesService = (OngoingMatchesService) getServletContext()
                .getAttribute("ongoingMatchesService");

        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        OngoingMatch match = ongoingMatchesService.getMatches().get(matchId);

        req.setAttribute("match", match);
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        int winnerId = Integer.parseInt(req.getParameter("winner"));
        try {
            OngoingMatch match = ongoingMatchesService.getMatches().get(matchId);
            Player winner = playerDao.getById(winnerId).orElseThrow(() -> new RuntimeException("Player not found"));

            match = matchScoreCalculationService.addPoint(match, winner);

            if(match.isFinished()){
                finishedMatchesPersistenceService.saveMatch(match);
                resp.sendRedirect(req.getContextPath() + "/matches");
            }else {
                req.setAttribute("match", match);
                req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
            }
        } catch (Exception e) {
        }
    }


}
