package service;

import model.Point;
import model.entity.OngoingMatch;
import model.entity.Player;

public class MatchScoreCalculationService {
    private static final int GAMES_FIVE = 5;
    private static final int GAMES_SIX = 6;
    private static final int GAMES_FOUR = 4;
    private static final int GAMES_ONE = 1;
    private static final int GAMES_ZERO = 0;

    private static final int SETS_ONE = 1;
    private static final int SETS_TWO = 2;

    private static final int MIN_DIFF = -1;
    private static final int TIEBREAK_LIMIT = 6;
    private static final int TIEBREAK_POINT = 1;

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

        if (current == GAMES_FIVE && opponent == GAMES_SIX) {
            setGame(match, isFirst, current + GAMES_ONE);
            match.getTieBreak().setTieBreak(true);
        } else if (current == GAMES_FIVE && opponent <= GAMES_FOUR) {
            setGame(match, isFirst, GAMES_ZERO);
            setGame(match, !isFirst, GAMES_ZERO);
            addSet(match, winner);
        } else if (current == GAMES_SIX && opponent == GAMES_FIVE) {
            setGame(match, isFirst, GAMES_ZERO);
            setGame(match, !isFirst, GAMES_ZERO);
            addSet(match, winner);
        } else {
            setGame(match, isFirst, current + GAMES_ONE);
        }

    }

    private void addSet(OngoingMatch match, Player winner) {
        boolean isFirst = match.getPlayer1().getId() == winner.getId();

        int current = getSets(match, isFirst);

        if (current == SETS_ONE) {
            setSet(match, isFirst, SETS_TWO);
            match.setWinner(winner);
            match.setFinished(true);
        } else {
            setSet(match, isFirst, current + SETS_ONE);
        }
    }

    private void tieBreak(OngoingMatch match, Player winner) {

        boolean isFirst = match.getPlayer1().getId() == winner.getId();

        int current = getTieBreakPoints(match, isFirst);
        int opponent = getTieBreakPoints(match, !isFirst);

        if ((current == opponent) || (current - opponent == MIN_DIFF) || (current < TIEBREAK_LIMIT)) {
            setTieBreakPoint(match, isFirst, current + TIEBREAK_POINT);
        } else {
            match.getTieBreak().reset();
            setGame(match, isFirst, GAMES_ZERO);
            setGame(match, !isFirst, GAMES_ZERO);
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
