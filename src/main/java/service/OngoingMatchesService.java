package service;

import dao.PlayerDao;
import lombok.Getter;
import lombok.Setter;
import model.entity.OngoingMatch;
import model.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class OngoingMatchesService {
    private Map<UUID, OngoingMatch> matches = new ConcurrentHashMap<>();
    private PlayerDao playerDao;

    public OngoingMatchesService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    private void addMatch(UUID matchId, OngoingMatch match) {
        getMatches().put(matchId, match);
    }

    public UUID registerMatch(String player1, String player2) {
        Player newPlayer1 = playerDao.getByName(player1).orElseGet(() -> {
                    Player p = new Player();
                    p.setName(player1);
                    playerDao.save(p);
                    return p;
                }
        );

        Player newPlayer2 = playerDao.getByName(player2).orElseGet(() -> {
                    Player p = new Player();
                    p.setName(player2);
                    playerDao.save(p);
                    return p;
                }
        );

        OngoingMatch match = new OngoingMatch();
        match.setPlayer1(newPlayer1);
        match.setPlayer2(newPlayer2);
        match.init();

        UUID matchId = UUID.randomUUID();
        addMatch(matchId, match);

        return matchId;
    }

}