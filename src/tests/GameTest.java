package tests;

import app.Game;
import app.RPIAlgorithm;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Pawel on 2016-06-08.
 */
public class GameTest {
    @Test
    public void mainTest() {
        RPIAlgorithm rpiAlgorithm = new RPIAlgorithm() {
            @Override
            public int computerselection() {    //ROCK
                return 1;
            }
        };

        Game game = new Game() {
            @Override
            public void checkAchievements() {

            }
        };
        game.setRpi(rpiAlgorithm);

        Assert.assertEquals(3, game.play(2));   //WIN
        Assert.assertEquals(4, game.play(3));   //LOOSE
        Assert.assertEquals(3, game.play(2));   //WIN
        Assert.assertEquals(5, game.play(1));   //DRAW

        Assert.assertEquals(2, Game.getWins());
        Assert.assertEquals(1, Game.getLosses());
        Assert.assertEquals(1, Game.getTies());
        Assert.assertEquals(4, Game.getTotalgames());
        Assert.assertEquals(1, game.finalScore());

        game.clearStats();

        Assert.assertEquals(0, Game.getWins());
        Assert.assertEquals(0, Game.getLosses());
        Assert.assertEquals(0, Game.getTies());
        Assert.assertEquals(0, Game.getTotalgames());
        Assert.assertEquals(0, game.finalScore());
    }
}