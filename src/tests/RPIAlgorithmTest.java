package tests;

import app.RPIAlgorithm;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Pawel on 2016-06-07.
 */
public class RPIAlgorithmTest {
    @Test
    public void rockTest() {
        RPIAlgorithm rpiAlgorithm = new RPIAlgorithm() {
            @Override
            public int computerselection() {    //ROCK
                return 1;
            }
        };

        Assert.assertEquals(3, rpiAlgorithm.RPS(2)); //PAPER > ROCK
        Assert.assertEquals(4, rpiAlgorithm.RPS(3)); //SCISSORS < ROCK
        Assert.assertEquals(5, rpiAlgorithm.RPS(1)); //ROCK == ROCK
    }

    @Test
    public void paperTest() {
        RPIAlgorithm rpiAlgorithm = new RPIAlgorithm() {
            @Override
            public int computerselection() {    //PAPER
                return 2;
            }
        };

        Assert.assertEquals(3, rpiAlgorithm.RPS(3)); //SCISSORS > PAPER
        Assert.assertEquals(4, rpiAlgorithm.RPS(1)); //ROCK < PAPER
        Assert.assertEquals(5, rpiAlgorithm.RPS(2)); //PAPER == PAPER
    }

    @Test
    public void scissorsTest() {
        RPIAlgorithm rpiAlgorithm = new RPIAlgorithm() {
            @Override
            public int computerselection() {    //SCISSORS
                return 3;
            }
        };

        Assert.assertEquals(3, rpiAlgorithm.RPS(1)); //ROCK > SCISSORS
        Assert.assertEquals(4, rpiAlgorithm.RPS(2)); //PAPER < SCISSORS
        Assert.assertEquals(5, rpiAlgorithm.RPS(3)); //SCISSORS == SCISSORS
    }
}