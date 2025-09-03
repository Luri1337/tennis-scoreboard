package service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OngoingMatch;
import model.Player;

import java.io.IOException;

public class MatchScoreCalculationService {

    public void addPoint(OngoingMatch match, Player winner, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(match.getPlayer1().getId() == winner.getId()) {
            match.setFirstPoints(match.getFirstPoints() + 15);

        }

        if (match.getPlayer2().getId() == winner.getId()) {
            if(match.getSecondPoints() == 0 || match.getSecondPoints() == 15){
                match.setSecondPoints(match.getSecondPoints() + 15);
            }
            else if (match.getSecondPoints() == 30 ) {
                match.setSecondPoints(match.getSecondPoints() + 10);
            }else if (match.getSecondPoints() == 40 && match.getFirstPoints() == 40) {

            }
            else {
                addSet(match, winner, req, resp);
            }

        }

        req.setAttribute("match", match);
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);


    }

    private void addSet(OngoingMatch match, Player winner, HttpServletRequest req, HttpServletResponse resp) {
    }

    private void addGame(OngoingMatch match, Player winner, HttpServletRequest req, HttpServletResponse resp) {
    }

}
