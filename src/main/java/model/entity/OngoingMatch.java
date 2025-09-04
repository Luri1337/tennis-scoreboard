package model.entity;

import lombok.Getter;
import lombok.Setter;
import model.Point;

@Getter
@Setter
public class OngoingMatch {

    private Point firstPoints;
    private int firstSets;
    private int firstGames;
    private Point secondPoints;
    private int secondSets;
    private int secondGames;
    private Player player1;
    private Player player2;

    public void init() {
        firstPoints = Point.ZERO;
        secondPoints = Point.ZERO;
    }
}
