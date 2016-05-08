import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * The Game class. Stores statistics.
 *
 * Created by Tim on 08/05/16.
 */
public class Game {

    static private int rockcount;
    static private int scissorcount;
    static private int papercount;

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
}
