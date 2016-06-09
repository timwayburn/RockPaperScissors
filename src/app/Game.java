package app;

/**
 * The app.Game class. Stores statistics during gameplay.
 * Also includes the play method.
 *
 * Created by Tim Wayburn & Philippa Örnell on 21/04/16.
 */
public class Game {

    static private int wins;
    static private int ties;
    static private int losses;
    static private int totalgames;

    private int lastwin;

    RPIAlgorithm rpi = new RPIAlgorithm();

    /**
     * @return the wins of the current game.
     */
    public static int getWins(){
        return wins;
    }

    /**
     * @return the losses of the current game.
     */
    public static int getLosses(){
        return losses;
    }

    /**
     * @return the ties of the current game.
     */
    public static int getTies(){
        return ties;
    }

    /**
     * @return the total rounds played in the current game.
     */
    public static int getTotalgames(){
        return totalgames;
    }


    /**
     * This method simulates a play of rock paper scissors.
     * It takes in a chosen play and returns the outcome.
     * Adds to the game's current counters based off result.
     *
     * @param  choice (1:rock 2:paper 3:scissors)
     * @return result of the play (3:win 4:loss 5:tie)
     */
    public int play(int choice){
        // choice: 1 = rock, 2 = paper, 3 = scissors
        // result: 3 = win, 4 = loss, 5 = tie
        int result = rpi.RPS(choice);
        if(result == 1 || result == 2 || result == 3){
            wins++;
        }
        else if (result == 4 || result == 5 || result == 6){
            losses++;
        }
        else {
            ties++;
        }
        totalgames++;
        checkAchievements();
        return result;
    }


    /**
     * This method checks how many wins the player has and displays an alertbox telling them they have made an achievement.
     */
    public void checkAchievements(){

        if(wins==1 && lastwin!=1){
            AlertBox.display("Achievement!", "Felicitaciones! You won your first game");
            lastwin = wins;
        }

        if(wins==5 && lastwin!=5){
            AlertBox.display("Achievement!", "Jajaja, you won 5 games");
            lastwin = wins;
        }


        if(wins==10 && lastwin!=10){
            AlertBox.display("Achievement!", "¡Hurra! You won 10 games");
            lastwin = wins;
        }
    }

    /**
     * @return the total wins minus total losses in the current game.
     */
    public int finalScore(){
        return wins - losses;
    }

    /**
     * Clears all the stats of the current game.
     */
    public void clearStats(){
        wins = 0;
        losses = 0;
        ties = 0;
        totalgames = 0;
    }

    public void setRpi(RPIAlgorithm rpi) {
        this.rpi = rpi;
    }
}