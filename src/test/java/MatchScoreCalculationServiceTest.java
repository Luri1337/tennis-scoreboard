import model.Point;
import model.entity.OngoingMatch;
import model.entity.Player;
import org.junit.jupiter.api.Test;
import service.MatchScoreCalculationService;

import static org.junit.jupiter.api.Assertions.*;


public class MatchScoreCalculationServiceTest {

    @Test
    void shouldNotFinishGameWhenScoreIsDeuceAndPlayerScores() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        Player player1 = new Player();
        player1.setName("Player1");
        player1.setId(1);
        Player player2 = new Player();
        player2.setName("Player2");
        player2.setId(2);

        //Case when first player earn point
        OngoingMatch match1 = new OngoingMatch();
        match1.setPlayer1(player1);
        match1.setPlayer2(player2);
        match1.init();
        match1.setFirstPoints(Point.FORTY);
        match1.setSecondPoints(Point.FORTY);


        match1 = service.addPoint(match1, player1);

        //Case when second player earn point

        OngoingMatch match2 = new OngoingMatch();
        match2.setPlayer1(player1);
        match2.setPlayer2(player2);
        match2.init();
        match2.setFirstPoints(Point.FORTY);
        match2.setSecondPoints(Point.FORTY);


        match2 = service.addPoint(match2, player2);

        OngoingMatch finalMatch1 = match1;
        OngoingMatch finalMatch2 = match2;
        assertAll(
                () -> assertEquals(finalMatch1.getFirstGames(), finalMatch1.getSecondGames()),
                () -> assertEquals(finalMatch2.getFirstGames(), finalMatch2.getSecondGames())
        );
    }

    @Test
    void shouldFinishGameWhenPlayerScoresAtFortyZero() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        Player player1 = new Player();
        player1.setName("Player1");
        player1.setId(1);
        Player player2 = new Player();
        player2.setName("Player2");
        player2.setId(2);

        //Case when first player earn point
        OngoingMatch match1 = new OngoingMatch();
        match1.setPlayer1(player1);
        match1.setPlayer2(player2);
        match1.init();
        match1.setFirstPoints(Point.FORTY);
        match1.setSecondPoints(Point.ZERO);


        match1 = service.addPoint(match1, player1);

        //Case when second player earn point

        OngoingMatch match2 = new OngoingMatch();
        match2.setPlayer1(player1);
        match2.setPlayer2(player2);
        match2.init();
        match2.setFirstPoints(Point.ZERO);
        match2.setSecondPoints(Point.FORTY);

        match2 = service.addPoint(match2, player2);

        OngoingMatch finalMatch1 = match1;
        OngoingMatch finalMatch2 = match2;
        assertAll(
                () -> assertNotEquals(finalMatch1.getFirstGames(), finalMatch1.getSecondGames()),
                () -> assertNotEquals(finalMatch2.getFirstGames(), finalMatch2.getSecondGames())
        );
    }

    @Test
    void shouldStartTieBreakIfGamesSixSix() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();

        Player player1 = new Player();
        player1.setName("Player1");
        player1.setId(1);
        Player player2 = new Player();
        player2.setName("Player2");
        player2.setId(2);

        //Case when first player earn point
        OngoingMatch match1 = new OngoingMatch();
        match1.setPlayer1(player1);
        match1.setPlayer2(player2);
        match1.init();
        match1.setFirstPoints(Point.FORTY);
        match1.setSecondPoints(Point.ZERO);
        match1.setFirstGames(5);
        match1.setSecondGames(6);

        match1 = service.addPoint(match1, player1);

        //Case when second player earn point

        OngoingMatch match2 = new OngoingMatch();
        match2.setPlayer1(player1);
        match2.setPlayer2(player2);
        match2.init();
        match2.setFirstPoints(Point.ZERO);
        match2.setSecondPoints(Point.FORTY);
        match2.setFirstGames(6);
        match2.setSecondGames(5);
        match2 = service.addPoint(match2, player2);

        OngoingMatch finalMatch1 = match1;
        OngoingMatch finalMatch2 = match2;
        assertAll(
                () -> assertTrue(finalMatch1.getTieBreak().getIsTieBreak()),
                () -> assertTrue(finalMatch2.getTieBreak().getIsTieBreak())
        );


    }

}
