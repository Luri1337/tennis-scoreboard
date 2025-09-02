package serlvet;

import dao.MatchDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;
import org.hibernate.SessionFactory;
import service.OngoingMatchesService;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
   private OngoingMatchesService ongoingMatchesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Match match = ongoingMatchesService.getMatches().get(req.getAttribute("uuid"));

        req.setAttribute("match", match);
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
    }

}
