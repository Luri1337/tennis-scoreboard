package service;

import model.Point;
import model.TieBreak;
import model.entity.OngoingMatch;
import model.entity.Player;

public class MatchScoreCalculationService {


    public OngoingMatch addPoint(OngoingMatch match, Player winner) {
        if (match.getTieBreak().getIsTieBreak()) {
            tieBreak(match, winner);
            return match;
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
                match.setFirstGames(match.getFirstGames() + 1);
                match.getTieBreak().setTieBreak(true);
            } else if (match.getFirstGames() == 5 && match.getSecondGames() <= 4) {
                match.setFirstGames(0);
                match.setSecondGames(0);
                addSet(match, winner);
            } else if (match.getFirstGames() == 6 && match.getSecondGames() == 5) {
                match.setFirstGames(0);
                match.setSecondGames(0);
                addSet(match, winner);
            }
            else {
                match.setFirstGames(match.getFirstGames() + 1);
            }
        }
        if (match.getPlayer2().getId() == winner.getId()) {
            if (match.getFirstGames() == 6 && match.getSecondGames() == 5) {
                match.setSecondGames(match.getSecondGames() + 1);
                match.getTieBreak().setTieBreak(true);
            } else if (match.getSecondGames() == 5 && match.getFirstGames() <= 4) {
                match.setSecondGames(0);
                match.setFirstGames(0);
                addSet(match, winner);
            } else if (match.getSecondGames() == 6 && match.getFirstGames() == 5) {
                match.setFirstGames(0);
                match.setSecondGames(0);
                addSet(match, winner);
            }
            else {
                match.setSecondGames(match.getSecondGames() + 1);
            }
        }

    }

    private void tieBreak(OngoingMatch match, Player winner) {
        TieBreak tieBreak = match.getTieBreak();
        if (match.getPlayer1().getId() == winner.getId()) {
            if ((tieBreak.getFirstPoints() == tieBreak.getSecondPoints())
                    || (tieBreak.getFirstPoints() - tieBreak.getSecondPoints() == -1)
                    || (tieBreak.getFirstPoints() < 6)) {
                tieBreak.setFirstPoints(tieBreak.getFirstPoints() + 1);
            }else{
                tieBreak.reset();
                match.setFirstGames(0);
                match.setSecondGames(0);
                addSet(match, winner);
            }

        }
        if (match.getPlayer2().getId() == winner.getId()) {
            if ((tieBreak.getFirstPoints() == tieBreak.getSecondPoints())
                    || (tieBreak.getSecondPoints() - tieBreak.getFirstPoints() == -1)
                    || (tieBreak.getSecondPoints() < 6)) {
                tieBreak.setSecondPoints(tieBreak.getSecondPoints() + 1);
            } else {
                tieBreak.reset();
                match.setFirstGames(0);
                match.setSecondGames(0);
                addSet(match, winner);
            }
        }
    }

    private void addSet(OngoingMatch match, Player winner) {
        if (match.getPlayer1().getId() == winner.getId()) {
            if (match.getFirstSets() == 1) {
                match.setFirstSets(2);
                match.setWinner(match.getPlayer1());
                match.setFinished(true);
            } else {
                match.setFirstSets(match.getFirstSets() + 1);
            }
        }

        if (match.getPlayer2().getId() == winner.getId()) {
            if (match.getSecondSets() == 1) {
                match.setSecondSets(2);
                match.setWinner(match.getPlayer2());
                match.setFinished(true);
            } else {
                match.setSecondSets(match.getSecondSets() + 1);
            }
        }
    }

}
