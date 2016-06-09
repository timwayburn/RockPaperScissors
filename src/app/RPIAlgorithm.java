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
     * @return result of the play (1,2,3:win 4,5,6:loss 7,8,9:tie -1:wrong input)
     */
    public int RPS(int choice){
        int compchoice = computerselection();

        plays.add(choice);
        compplays.add(compchoice);

        if(choice == compchoice){
            if(choice == 1) {
                return 7;   //Rock tie
            }
            else if(choice == 2) {
                return 8;   //Paper tie
            }
            else if(choice == 3) {
                return 9;   //Scissors tie
            }
        }
        else if(choice == 1 && compchoice == 2){
            return 4;   //Rock < Paper
        }
        else if(choice == 1 && compchoice == 3){
            return 1;   //Rock > Scissors
        }
        else if(choice == 2 && compchoice == 1){
            return 2;   //Paper > Rock
        }
        else if(choice == 2 && compchoice == 3){
            return 5;   //Paper < Scissors
        }
        else if(choice == 3 && compchoice == 1){
            return 6;   //Scissors < Rock
        }
        else if(choice == 3 && compchoice == 2){
            return 3;   //Scissors > Paper
        }
        return -1;
    }

    /**
     * @return the computers throw (1:rock 2:paper 3:scissors)
     */
    public int computerselection(){
        int compchoice = random.nextInt(3) + 1;
        return compchoice;
    }

}
