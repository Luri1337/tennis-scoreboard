package service;

import lombok.Getter;
import lombok.Setter;
import model.entity.OngoingMatch;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class OngoingMatchesService {
    private Map<UUID, OngoingMatch> matches = new ConcurrentHashMap<>();

    public void addMatch(UUID matchId, OngoingMatch match) {
        getMatches().put(matchId, match);
    }
}