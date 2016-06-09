package tests;

import app.Game;
import org.junit.Test;

/**
 * Created by Stach on 2016-06-09.
 */
public class DistributionTest {
    @Test
    public void mainTest() {
        Game game = new Game(){
            @Override
            public void checkAchievements() {
                //GUI Stuff
            }
        };

        manyRuns(game, 1);
        displayscores(game);
        game.clearStats();

        manyRuns(game, 2);
        displayscores(game);
        game.clearStats();

        manyRuns(game, 3);
        displayscores(game);
    }

    private static void manyRuns(Game game, int choice){
        for (int i=0; i<1000000; i++){
            game.play(choice);
        }
    }

    private static void displayscores(Game game){
        System.out.println("Wins: " + Game.getWins() + "  Ties: " + Game.getTies() + "  Losses: " + Game.getLosses());
    }
}
