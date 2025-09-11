package servlet;

import dao.PlayerDao;
import dto.validationDto.MatchPlayersContext;
import exception.ExceptionHandler;
import exception.InvalidNameFormat;
import exception.MissingRequiredParameterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.OngoingMatch;
import model.entity.Player;
import org.hibernate.SessionFactory;
import service.OngoingMatchesService;
import util.validation.NewMatchValidator;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private PlayerDao playerDao;
    private OngoingMatchesService ongoingMatchesService;
    private NewMatchValidator validator;


    @Override
    public void init() {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute("ongoingMatchesService");
        playerDao = new PlayerDao(sessionFactory);
        validator = new NewMatchValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1 = req.getParameter("player1");
        String player2 = req.getParameter("player2");

        try {
            validator.validate(new MatchPlayersContext(player1, player2));
            Player newPlayer1 = new Player();
            newPlayer1.setName(player1);
            Player newPlayer2 = new Player();
            newPlayer2.setName(player2);
            playerDao.save(newPlayer1);
            playerDao.save(newPlayer2);

            OngoingMatch match = new OngoingMatch();
            match.setPlayer1(newPlayer1);
            match.setPlayer2(newPlayer2);
            match.init();

            UUID matchId = UUID.randomUUID();
            ongoingMatchesService.addMatch(matchId, match);

            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchId);


        } catch (MissingRequiredParameterException | InvalidNameFormat e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


}
