package servlet;

import dao.PlayerDao;
import dto.validationDto.MatchScoreUpdateContext;
import exception.ExceptionHandler;
import exception.InvalidIdFormat;
import exception.MissingRequiredParameterException;
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
import util.AppMassages;
import util.validation.MatchScoreValidator;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private PlayerDao playerDao;
    private MatchScoreValidator validator;

    @Override
    public void init() {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        matchScoreCalculationService = (MatchScoreCalculationService) getServletContext().getAttribute("matchScoreService");
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesService");
        playerDao = new PlayerDao(sessionFactory);
        validator = new MatchScoreValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchId = req.getParameter("uuid");

        try {
            OngoingMatch match = ongoingMatchesService.getMatches().get(UUID.fromString(matchId));

            req.setAttribute("match", match);
            req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
        } catch (MissingRequiredParameterException e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (IllegalArgumentException e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_BAD_REQUEST, AppMassages.INVALID_ID);
        } catch (Exception e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matchId = req.getParameter("uuid");
        String winnerId = req.getParameter("winner");
        try {
            validator.validatePostMethod(new MatchScoreUpdateContext(winnerId));

            OngoingMatch match = ongoingMatchesService.getMatches().get(UUID.fromString(matchId));
            Player winner = playerDao.getById(Integer.parseInt(winnerId)).orElseThrow(() -> new RuntimeException("Player not found"));

            match = matchScoreCalculationService.addPoint(match, winner);

            if (match.isFinished()) {
                finishedMatchesPersistenceService.saveMatch(match);
                resp.sendRedirect(req.getContextPath() + "/matches?page=1&filter=");
            } else {
                req.setAttribute("match", match);
                req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
            }
        } catch (MissingRequiredParameterException | InvalidIdFormat e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
