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
 * Test Application :)
 *
 * Created by Tim on 21/04/16.
 */
public class Main extends Application {

    Scene scene1, scene2, scene3;
    Stage window;
    GridPane grid;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage; // primary stage
        window.setTitle("RockPaperScissors Bot"); // Window title (top of screen)

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });



        // Layout 1 - start screen

            //Button 1 (go to main menu)
        Button button1 = new Button("Click here to start"); // Create a button
        button1.setOnAction(e -> window.setScene(scene2));

            // Button 3 (exit button)
        Button button3 = new Button("Exit");
        button3.setOnAction(e -> closeProgram());

        Label label1 = new Label("Welcome to Rock Paper Scissors!");

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label1, button1, button3);
        scene1 = new Scene(layout, 400, 300); // create the scene, set size of entire window



        // Layout 2 - Main menu

            // Button 2 (to highscore button)
        Button button2 = new Button("To Highscores");
        button2.setOnAction(e -> window.setScene(scene3));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 400, 300);


        // Layout 4 - To highscore submit

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

            // Name Label
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);

            // Name input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);
        nameInput.setPromptText("Your name here");
        //TODO: red outline if no name or too long.

            // Button 4 - submit highscore
        Button button4 = new Button("Submit score");
        GridPane.setConstraints(button4, 1, 2);
        //TODO: highscorelist

        grid.getChildren().addAll(nameLabel, nameInput, button4);
        scene3 = new Scene(grid, 400, 300);

        //Layout 5 - Options menu
            //TODO: Options menu with checkboxes for resolutions, modes, difficulty.


        // startup
        primaryStage.setScene(scene1); // set the main scene on stage
        primaryStage.show();
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("title", "Are you sure you want to exit?");

        if(answer){
            Platform.exit();
        }
    }


}
