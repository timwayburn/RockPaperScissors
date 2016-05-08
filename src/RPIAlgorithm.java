import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tim on 23/04/16.
 */
public class RPIAlgorithm {

    ArrayList<Integer> plays = new ArrayList<>();
    ArrayList<Integer> compplays = new ArrayList<>();
    Random random = new Random();

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

    public int computerselection(){
        int compchoice = random.nextInt(3) + 1;
        return compchoice;
    }

}
