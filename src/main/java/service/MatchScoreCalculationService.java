package service;

import model.Point;
import model.entity.OngoingMatch;
import model.entity.Player;

public class MatchScoreCalculationService {


    public OngoingMatch addPoint(OngoingMatch match, Player winner) {
        boolean isFirst = match.getPlayer1().getId() == winner.getId();

        Point current = getPoints(match, isFirst);
        Point opponent = getPoints(match, !isFirst);

        if (match.getTieBreak().getIsTieBreak()) {
            tieBreak(match, winner);
            return match;
        }

        if (current == Point.ZERO) {
            setPoint(match, isFirst, Point.FIFTEEN);
        } else if (current == Point.FIFTEEN) {
            setPoint(match, isFirst, Point.THIRTY);
        } else if (current == Point.THIRTY) {
            setPoint(match, isFirst, Point.FORTY);
        } else if (current == Point.FORTY && opponent == Point.FORTY) {
            setPoint(match, isFirst, Point.ADVANCED);
        } else if (current == Point.FORTY && opponent == Point.ADVANCED) {
            setPoint(match, !isFirst, Point.FORTY);
        } else {
            setPoint(match, isFirst, Point.ZERO);
            setPoint(match, !isFirst, Point.ZERO);
            addGame(match, winner);
        }

        return match;
    }


    private void addGame(OngoingMatch match, Player winner) {
        boolean isFirst = match.getPlayer1().getId() == winner.getId();

        int current = getGames(match, isFirst);
        int opponent = getGames(match, !isFirst);

        if (current == 5 && opponent == 6) {
            setGame(match, isFirst, current + 1);
            match.getTieBreak().setTieBreak(true);
        } else if (current == 5 && opponent <= 4) {
            setGame(match, isFirst, 0);
            setGame(match, !isFirst, 0);
            addSet(match, winner);
        } else if (current == 6 && opponent == 5) {
            setGame(match, isFirst, 0);
            setGame(match, !isFirst, 0);
            addSet(match, winner);
        } else {
            setGame(match, isFirst, current + 1);
        }

    }

    private void addSet(OngoingMatch match, Player winner) {
        boolean isFirst = match.getPlayer1().getId() == winner.getId();

        int current = getSets(match, isFirst);

        if (current == 1) {
            setSet(match, isFirst, 2);
            match.setWinner(winner);
            match.setFinished(true);
        } else {
            setSet(match, isFirst, current + 1);
        }
    }

    private void tieBreak(OngoingMatch match, Player winner) {

        boolean isFirst = match.getPlayer1().getId() == winner.getId();

        int current = getTieBreakPoints(match, isFirst);
        int opponent = getTieBreakPoints(match, !isFirst);

        if ((current == opponent) || (current - opponent == -1) || (current < 6)) {
            setTieBreakPoint(match, isFirst, current + 1);
        } else {
            match.getTieBreak().reset();
            setGame(match, isFirst, 0);
            setGame(match, !isFirst, 0);
            addSet(match, winner);
        }

    }

    private Point getPoints(OngoingMatch match, boolean isFirst) {
        return isFirst ? match.getFirstPoints() : match.getSecondPoints();
    }

    private void setPoint(OngoingMatch match, boolean isFirst, Point value) {
        if (isFirst) {
            match.setFirstPoints(value);
        } else {
            match.setSecondPoints(value);
        }

    }

    private int getGames(OngoingMatch match, boolean isFirst) {
        return isFirst ? match.getFirstGames() : match.getSecondGames();
    }

    private void setGame(OngoingMatch match, boolean isFirst, int value) {
        if (isFirst) {
            match.setFirstGames(value);
        } else {
            match.setSecondGames(value);
        }
    }

    private int getSets(OngoingMatch match, boolean isFirst) {
        return isFirst ? match.getFirstSets() : match.getSecondSets();
    }

    private void setSet(OngoingMatch match, boolean isFirst, int value) {
        if (isFirst) {
            match.setFirstSets(value);
        } else {
            match.setSecondSets(value);
        }
    }

    private int getTieBreakPoints(OngoingMatch match, boolean isFirst) {
        return isFirst ? match.getTieBreak().getFirstPoints() : match.getTieBreak().getSecondPoints();
    }

    private void setTieBreakPoint(OngoingMatch match, boolean isFirst, int value) {
        if (isFirst) {
            match.getTieBreak().setFirstPoints(value);
        } else {
            match.getTieBreak().setSecondPoints(value);
        }
    }

}
