package service;

import dao.MatchDao;
import lombok.Setter;
import model.entity.FinishedMatch;
import model.entity.OngoingMatch;

import java.util.List;
@Setter
public class FinishedMatchesPersistenceService {
    private MatchDao matchDao;
    public FinishedMatchesPersistenceService(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    public void saveMatch(OngoingMatch match) {
        FinishedMatch finishedMatch = new FinishedMatch();
        finishedMatch.setPlayer1(match.getPlayer1());
        finishedMatch.setPlayer2(match.getPlayer2());
        finishedMatch.setWinner(match.getWinner());

        matchDao.save(finishedMatch);
    }
}
