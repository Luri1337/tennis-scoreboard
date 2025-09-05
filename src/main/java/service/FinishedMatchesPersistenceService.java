package service;

import dao.MatchDao;
import model.entity.FinishedMatch;
import model.entity.OngoingMatch;
import model.entity.Player;

public class FinishedMatchesPersistenceService {

    public void saveMatch(OngoingMatch match, MatchDao matchDao) {
        FinishedMatch finishedMatch = new FinishedMatch();
        finishedMatch.setPlayer1(match.getPlayer1());
        finishedMatch.setPlayer2(match.getPlayer2());
        finishedMatch.setWinner(match.getWinner());

        matchDao.save(finishedMatch);
    }
}
