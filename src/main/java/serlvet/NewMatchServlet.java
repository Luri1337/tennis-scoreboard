package serlvet;

import dao.MatchDao;
import dao.PlayerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;
import model.Player;
import org.hibernate.SessionFactory;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private PlayerDao playerDao;
    private OngoingMatchesService ongoingMatchesService;


    @Override
    public void init() throws ServletException {
        SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        playerDao = new PlayerDao(sessionFactory);
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
            Player newPlayer1 = playerDao.getByName(player1).orElseThrow(() -> new RuntimeException("Player not found"));
            Player newPlayer2 = playerDao.getByName(player2).orElseThrow(() -> new RuntimeException("Player not found"));


            Match match = new Match();
            match.setPlayer1(newPlayer1);
            match.setPlayer2(newPlayer2);

            UUID matchId = UUID.randomUUID();
            ongoingMatchesService.addMatch(matchId, match);


            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchId);





        } catch (Exception ignored) {
        }
    }


}
