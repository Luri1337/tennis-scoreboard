package servlet;

import dto.MatchesPageDto;
import dto.validationDto.PageFilterContextDto;
import exception.ExceptionHandler;
import exception.InvalidFilterFormat;
import exception.InvalidPageFormat;
import exception.MissingRequiredParameterException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchListingService;
import util.validation.MatchesValidator;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private MatchListingService matchListingService;
    private MatchesValidator validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        String currentPage = req.getParameter("page");

        try {
            validator.validate(new PageFilterContextDto(filter, currentPage));

            MatchesPageDto pageDto = matchListingService.getMatchesPageDto(filter, currentPage);

            req.setAttribute("matches", pageDto.getMatches());
            req.setAttribute("currentPage", pageDto.getCurrentPage());
            req.setAttribute("totalPages", pageDto.getTotalPages());
            req.setAttribute("filter", pageDto.getFilter());

            req.getRequestDispatcher("/WEB-INF/jsp/matches.jsp").forward(req, resp);
        } catch (InvalidFilterFormat | InvalidPageFormat | MissingRequiredParameterException e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }catch (Exception e) {
            ExceptionHandler.handleException(resp, req, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void init() {
        matchListingService = (MatchListingService) getServletContext().getAttribute("matchListingService");
        validator = new MatchesValidator();
    }
}
