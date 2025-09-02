package service;

import lombok.Getter;
import lombok.Setter;
import model.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class OngoingMatchesService {
    private Map<UUID, Match> matches = new ConcurrentHashMap();

    public void addMatch(UUID matchId, Match match) {
        getMatches().put(matchId, match);
    }
}