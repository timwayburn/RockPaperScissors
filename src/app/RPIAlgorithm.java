package app;

import java.util.ArrayList;
import java.util.Random;

/**
 * Handles the computer's selection of Rock, Paper or Scissors.
 *
 * Created by Tim Wayburn & Philippa Örnell on 21/04/16.
 */
public class RPIAlgorithm {

    ArrayList<Integer> plays = new ArrayList<>();
    ArrayList<Integer> compplays = new ArrayList<>();
    Random random = new Random();

    /**
     * The method that is called within the play method in the app.Game class.
     * Compares the computers choice with the players choice
     *
     * @param choice of player (1:rock 2:paper 3:scissors)
     * @return result of the play (3:win 4:loss 5:tie)
     */
    public int RPS(int choice){
        // parameter choice: 1 = rock, 2 = paper, 3 = scissors
        //  returns result: 3 = win, 4 = loss, 5 = tie
        int compchoice = computerselection();

        plays.add(choice);
        compplays.add(compchoice);

        if(choice == compchoice){
            return 5; // tie
        }
        else if(choice == 1 && compchoice == 2){
            return 4;
        }
        else if(choice == 1 && compchoice == 3){
            return 3;
        }
        else if(choice == 2 && compchoice == 1){
            return 3;
        }
        else if(choice == 2 && compchoice == 3){
            return 4;
        }
        else if(choice == 3 && compchoice ==1){
            return 4;
        }
        else {  //choice == 3 && compchoice == 2
            return 3;
        }
    }

    /**
     * @return the computers throw (1:rock 2:paper 3:scissors)
     */
    public int computerselection(){
        int compchoice = random.nextInt(3) + 1;
        return compchoice;
    }

}
