package serlvet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.FinishedMatch;
import service.FinishedMatchesPersistenceService;


import java.io.IOException;
import java.util.List;
//TODO сделать поиск по фильтру с буквой, сделать так чтобы когда задавался фильтр пагинация тоже работала.
// Отрефакторить, сделать сервлеты более чистыми
// сделать сервис по подсчету очков не зависящим от конкретного плеера
// добавить валидаторы, обработчики исключений
// фронтенд захуярить самому
// вынести магические числа в переменные
// деплой
//

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = 1;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try{
                currentPage = Integer.parseInt(pageParam);
            }catch (NumberFormatException e){
                currentPage = 1;
            }
        }
        String filter = req.getParameter("filter");
        List <FinishedMatch> matches;

        if (filter != null && !filter.isEmpty()) {
            matches = finishedMatchesPersistenceService.getMatchesByFilter(filter);
        }else{
            matches = finishedMatchesPersistenceService.getMatchesByPage(currentPage);
        }

        List <FinishedMatch> allMatches = finishedMatchesPersistenceService.getAllMatches();
        double totalPages =Math.ceil ((double) allMatches.size() / 10);


        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("filter", filter);

        req.getRequestDispatcher("/WEB-INF/jsp/matches.jsp").forward(req, resp);
    }

    @Override
    public void init(){
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesService");
    }
}
