

/**
 * The Game class. Stores statistics.
 *
 * Created by Tim on 08/05/16.
 */
public class Game {

    static private int wins;
    static private int ties;
    static private int losses;
    static private int totalgames;

    RPIAlgorithm rpi = new RPIAlgorithm();

    public static int getWins(){
        return wins;
    }

    public static int getLosses(){
        return losses;
    }

    public static int getTies(){
        return ties;
    }

    public static int getTotalgames(){
        return totalgames;
    }


    public int play(int choice){
        // choice: 1 = rock, 2 = paper, 3 = scissors
        // result: 3 = win, 4 = loss, 5 = tie
       int result = rpi.RPS(choice);
        if(result == 3){
            wins++;
        }
        else if (result == 4){
            losses++;
        }
        else{
            ties++;
        }
        totalgames++;
        return result;
    }

    public int finalScore(){
        return wins - losses;
    }

    public void clearStats(){
        wins = 0;
        losses = 0;
        ties = 0;
        totalgames = 0;
    }
}
