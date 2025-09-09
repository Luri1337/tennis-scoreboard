package service;

import dao.MatchDao;
import model.entity.FinishedMatch;

import java.util.List;

public class MatchListingService {
    private final MatchDao matchDao;

    public MatchListingService(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    public List<FinishedMatch> getMatches(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return getMatchesByFilter(filter);
        } else {
            return matchDao.getAll();
        }
    }

    private List<FinishedMatch> getMatchesByFilter(String filter) {
        return matchDao.getByName(filter);
    }

    public double getTotalPages(List<FinishedMatch> matches) {
        return Math.ceil((double) matches.size() / 10);
    }
}
