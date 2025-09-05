package service;

import model.Point;
import model.TieBreak;
import model.entity.OngoingMatch;
import model.entity.Player;

public class MatchScoreCalculationService {


    public OngoingMatch addPoint(OngoingMatch match, Player winner) {
        if (match.getTieBreak().isTieBreak()) {
            tieBreak(match, winner);
        }
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
                addGame(match, winner);
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
                addGame(match, winner);
            }

        }
        return match;
    }


    private void addGame(OngoingMatch match, Player winner) {
        if (match.getPlayer1().getId() == winner.getId()) {
            if (match.getFirstGames() == 5 && match.getSecondGames() == 6) {
                match.setFirstGames(match.getSecondGames() + 1);
                match.getTieBreak().setTieBreak(true);
                tieBreak(match, winner);
            } else if (match.getFirstGames() == 5 && match.getSecondGames() <= 4) {
                match.setFirstGames(0);
                addSet(match, winner);
            } else {
                match.setFirstGames(match.getFirstGames() + 1);
            }
        }
        if (match.getPlayer2().getId() == winner.getId()) {
            if (match.getFirstGames() == 6 && match.getSecondGames() == 5) {
                match.setSecondGames(match.getSecondGames() + 1);
                match.getTieBreak().setTieBreak(true);
                tieBreak(match, winner);
            } else if (match.getSecondGames() == 5 && match.getFirstGames() <= 4) {
                match.setSecondGames(0);
                addSet(match, winner);
            } else {
                match.setSecondGames(match.getSecondGames() + 1);
            }
        }

    }

    private void tieBreak(OngoingMatch match, Player winner) {
        TieBreak tieBreak = match.getTieBreak();
        if (match.getPlayer1().getId() == winner.getId()) {
            if ((tieBreak.getFirstPoints() == tieBreak.getSecondPoints())
                    || (tieBreak.getFirstPoints() - tieBreak.getSecondPoints() == -1)){
                tieBreak.setFirstPoints(tieBreak.getFirstPoints() + 1);
            }else{
                tieBreak.setFirstPoints(tieBreak.getFirstPoints() + 1);
                match.setTieBreak(tieBreak);
                addSet(match, winner);
            }

        }
        if (match.getPlayer2().getId() == winner.getId()) {
            if ((tieBreak.getFirstPoints() == tieBreak.getSecondPoints())
                    || (tieBreak.getSecondPoints() - tieBreak.getFirstPoints() == -1)) {
                tieBreak.setSecondPoints(tieBreak.getSecondPoints() + 1);
            } else {
                tieBreak.setSecondPoints(tieBreak.getSecondPoints() + 1);
                match.setTieBreak(tieBreak);
                addSet(match, winner);
            }
        }
    }

    private void addSet(OngoingMatch match, Player winner) {
        if (match.getPlayer1().getId() == winner.getId()) {
            if (match.getFirstGames() == 1) {
                match.setFirstGames(2);
                match.setWinner(match.getPlayer1());
                match.setFinished(true);
            } else {
                match.setFirstSets(match.getFirstGames() + 1);
            }
        }

        if (match.getPlayer2().getId() == winner.getId()) {
            if (match.getSecondGames() == 1) {
                match.setSecondGames(2);
                match.setWinner(match.getPlayer2());
                match.setFinished(true);
            } else {
                match.setSecondSets(match.getSecondGames() + 1);
            }
        }
    }

}
