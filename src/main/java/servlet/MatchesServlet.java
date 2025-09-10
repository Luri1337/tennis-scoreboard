package servlet;

import dto.MatchesPageDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchListingService;


import java.io.IOException;

//TODO
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
        String filter = req.getParameter("filter");
        String currentPage = req.getParameter("page");

        MatchesPageDto pageDto = matchListingService.getMatchesPageDto(filter, currentPage);

        req.setAttribute("matches", pageDto.getMatches());
        req.setAttribute("currentPage", pageDto.getCurrentPage());
        req.setAttribute("totalPages", pageDto.getTotalPages());
        req.setAttribute("filter", pageDto.getFilter());

        req.getRequestDispatcher("/WEB-INF/jsp/matches.jsp").forward(req, resp);
    }

    @Override
    public void init(){
        matchListingService = (MatchListingService) getServletContext().getAttribute("matchListingService");
    }
}
