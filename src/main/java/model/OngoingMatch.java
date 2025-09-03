package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OngoingMatch {

    private int firstPoints;
    private int firstSets;
    private int firstGames;
    private int secondPoints;
    private int secondSets;
    private int secondGames;
    private Player player1;
    private Player player2;
}
