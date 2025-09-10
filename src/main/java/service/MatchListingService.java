package service;

import dao.MatchDao;
import dto.MatchesPageDto;
import model.entity.FinishedMatch;

import java.util.List;

public class MatchListingService {
    private static final int PAGE_VALUE = 1;
    private static final int PAGE_LENGTH = 10;
    private final MatchDao matchDao;

    public MatchListingService(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    public MatchesPageDto getMatchesPageDto(String filter, String pageParam) {
        int currentPage = PAGE_VALUE;

        if (pageParam != null) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<FinishedMatch> matches = getMatches(filter);


        double totalPages = getTotalPages(matches);

        if (currentPage > totalPages) {
            currentPage = PAGE_VALUE;
        }

        int min = Math.min(currentPage * PAGE_LENGTH, matches.size());
        matches = matches.subList((currentPage - PAGE_VALUE) * PAGE_LENGTH, min);

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
        return Math.ceil((double) matches.size() / PAGE_LENGTH);
    }
}
