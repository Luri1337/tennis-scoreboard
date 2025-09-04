package service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Point;
import model.entity.OngoingMatch;
import model.entity.Player;

import java.io.IOException;

public class MatchScoreCalculationService {


    public void addPoint(OngoingMatch match,
                         Player winner,
                         HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        if (match.getPlayer1().getId() == winner.getId()) {
            if (match.getFirstPoints().getValue().equals("0")) {
                match.setFirstPoints(Point.FIFTEEN);
            } else if (match.getFirstPoints().getValue().equals("15")) {
                match.setFirstPoints(Point.THIRTEEN);
            } else if (match.getFirstPoints().getValue().equals("30")) {
                match.setFirstPoints(Point.FORTY);
            } else if (match.getSecondPoints().getValue().equals("40")
                    && match.getFirstPoints().getValue().equals("40")) {
                match.setFirstPoints(Point.ADVANCED);
            } else if (match.getSecondPoints().getValue().equals("AD")
                    && match.getFirstPoints().getValue().equals("40")) {
                match.setSecondPoints(Point.FORTY);
                match.setFirstPoints(Point.FORTY);
            } else {
                match.setSecondPoints(Point.ZERO);
                match.setFirstPoints(Point.ZERO);
                addGame(match, winner, req, resp);
            }
        }

        if (match.getPlayer2().getId() == winner.getId()) {
            if (match.getSecondPoints().getValue().matches("0")) {
                match.setSecondPoints(Point.FIFTEEN);
            } else if (match.getSecondPoints().getValue().matches("15")) {
                match.setSecondPoints(Point.THIRTEEN);
            } else if (match.getSecondPoints().getValue().matches("30")) {
                match.setSecondPoints(Point.FORTY);
            } else if (match.getSecondPoints().getValue().matches("40")
                    && match.getFirstPoints().getValue().matches("40")) {
                match.setSecondPoints(Point.ADVANCED);
            } else if (match.getSecondPoints().getValue().matches("40")
                    && match.getFirstPoints().getValue().matches("AD")) {
                match.setSecondPoints(Point.FORTY);
                match.setFirstPoints(Point.FORTY);
            } else {
                match.setSecondPoints(Point.ZERO);
                match.setFirstPoints(Point.ZERO);
                addGame(match, winner, req, resp);
            }

        }

        req.setAttribute("match", match);
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);


    }



    private void addGame(OngoingMatch match, Player winner, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (match.getPlayer1().getId() == winner.getId()) {
           if (match.getFirstGames() == 6 && match.getSecondGames() == 6) {
               tieBreak(match, winner, req, resp);
           } else if (match.getFirstGames() == 5) {
               match.setFirstGames(0);
              addSet(match, winner, req, resp);
           }else{
               match.setFirstGames(match.getFirstGames() + 1);
           }
        }
        if (match.getPlayer2().getId() == winner.getId()) {
            if (match.getFirstGames() == 6 && match.getSecondGames() == 6) {
                tieBreak(match, winner, req, resp);
            } else if (match.getSecondGames() == 5) {
                match.setSecondGames(0);
                addSet(match, winner, req, resp);
            }else{
                match.setSecondGames(match.getSecondGames() + 1);
            }
        }

        req.setAttribute("match", match);
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
    }

    private void tieBreak(OngoingMatch match, Player winner, HttpServletRequest req, HttpServletResponse resp) {
    }

    private void addSet(OngoingMatch match, Player winner, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (match.getPlayer1().getId() == winner.getId()) {
            if (match.getFirstGames() == 1){
                // finish match
            }
            else {
                match.setFirstSets(match.getFirstGames() + 1);
            }
        }

        if (match.getPlayer2().getId() == winner.getId()) {
            if (match.getSecondGames() == 1){
                //finish match
            }
            else {
                match.setSecondSets(match.getSecondGames() + 1);
            }
        }
        req.setAttribute("match", match);
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);

    }

}
