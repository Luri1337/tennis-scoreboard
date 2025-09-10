package service;

import dao.MatchDao;
import dto.MatchesPageDto;
import model.entity.FinishedMatch;

import java.util.List;

public class MatchListingService {
    private final MatchDao matchDao;

    public MatchListingService(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    public MatchesPageDto getMatchesPageDto(String filter, String pageParam) {
        int currentPage = 1;

        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<FinishedMatch> matches = getMatches(filter);


        double totalPages = getTotalPages(matches);

        if (currentPage > totalPages) {
            currentPage = 1;
        }

        int min = Math.min(currentPage * 10, matches.size());
        matches = matches.subList((currentPage - 1) * 10, min);

        return new MatchesPageDto(matches, totalPages, currentPage, filter);
    }

    private List<FinishedMatch> getMatches(String filter) {

        if (filter != null && !filter.isEmpty()) {
            return getMatchesByFilter(filter);
        } else {
            return matchDao.getAll();
        }
    }

    private List<FinishedMatch> getMatchesByFilter(String filter) {
        return matchDao.getByName(filter);
    }

    private double getTotalPages(List<FinishedMatch> matches) {
        return Math.ceil((double) matches.size() / 10);
    }
}
