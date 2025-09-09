package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.FinishedMatch;
import service.MatchListingService;


import java.io.IOException;
import java.util.List;
//TODO сделать так чтобы когда задавался фильтр пагинация тоже работала.
// Отрефакторить, сделать сервлеты более чистыми
// сделать сервис по подсчету очков не зависящим от конкретного плеера
// добавить валидаторы, обработчики исключений
// фронтенд захуярить самому
// вынести магические числа в переменные
// деплой
//

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private MatchListingService matchListingService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = 1;

        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        String filter = req.getParameter("filter");
        List <FinishedMatch> matches = matchListingService.getMatches(filter);


        double totalPages = matchListingService.getTotalPages(matches);

        if (currentPage > totalPages) {
            currentPage = 1;
        }

        int min = Math.min(currentPage * 10, matches.size());
        matches =  matches.subList((currentPage - 1) * 10, min);

        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("filter", filter);

        req.getRequestDispatcher("/WEB-INF/jsp/matches.jsp").forward(req, resp);
    }

    @Override
    public void init(){
        matchListingService = (MatchListingService) getServletContext().getAttribute("matchListingService");
    }
}
