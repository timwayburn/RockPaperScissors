package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.*;
import java.util.Random;


/**
 * The main class of our Rock Paper Scissors game. Run this Class to start the game.
 *
 * Created by Tim Wayburn & Philippa Örnell on 21/04/16.
 */
public class Main extends Application {

    Scene scene1, scene2, scene3, scene4;
    Game game;
    Label highscoretitle;
    MediaPlayer player;
    static int highScore;
    static String highScoreName;
    static String name;
    HighScoreManager highScoreManager;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Called upon start of program.
     * (Could be condensed by breaking up into methods, or by remaking in Scene Builder)
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Highscore path
        try{
            String saveDataPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            highScoreManager = new HighScoreManager(saveDataPath);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        loadHighScore();// Load from highscore file upon game start.


        Stage window = primaryStage; // primary stage
        window.setTitle("Rock Paper Scissors"); // Window title (top of screen)
        game = new Game();

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });



        // Layout 1 - app.Main menu

        //music for main menu
        Media media = new Media(this.getClass().getClassLoader().getResource("app/Sounds/menumusic.mp3").toExternalForm().toString());
        player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setStartTime(Duration.seconds(8));
        player.play();
        player.setVolume(0.1);

        //Button 1 (start game)
        Button button1 = new Button("Click here to start"); // Create a button
        button1.setOnAction(e -> {
            game.clearStats();
            window.setScene(scene2);
            player.stop();
        });


        // Button 3 (exit button)
        Button button3 = new Button("Exit");
        button3.setOnAction(e -> {
            closeProgram();
        });

        Label label1 = new Label("Welcome to Rock Paper Scissors!");
        Label labelmain1 = new Label("¡VIVA");
        Label labelmain2 = new Label("MEXICO!");

        MediaView mediaView = new MediaView(player);

        HBox layout1full = new HBox(100);
        layout1full.setAlignment(Pos.CENTER);

        VBox layout1 = new VBox(0);
        VBox layout2 = new VBox(10);
        VBox layout3 = new VBox(0);

        layout1.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);
        layout3.setAlignment(Pos.CENTER);

        layout1.getChildren().add(labelmain1);
        layout2.getChildren().addAll(label1, button1, button3, mediaView);
        layout3.getChildren().add(labelmain2);

        layout1full.getChildren().addAll(layout1,layout2,layout3);
        layout1full.setStyle("-fx-background: #00AA00;");
        scene1 = new Scene(layout1full, 1080, 960); // create the scene, set size of entire window



        // Layout 2 - Gameplay

        GridPane gamegrid = new GridPane();
        gamegrid.setPadding(new Insets(10,10,10,10));
        gamegrid.setVgap(8);
        gamegrid.setHgap(10);

        // Wins label
        Label winslabel = new Label("Wins: " + game.getWins());
        gamegrid.setConstraints(winslabel, 1, 2);
        // Tie label
        Label tielabel = new Label("Ties: " + game.getTies());
        gamegrid.setConstraints(tielabel, 2, 2);
        // Loss label
        Label losslabel = new Label("Losses: " + game.getLosses());
        gamegrid.setConstraints(losslabel, 3, 2);
        // Totalgames label
        Label totallabel = new Label("Total Games played: " + game.getTotalgames());
        gamegrid.setConstraints(totallabel, 0, 1);


        // Button 2 (end game button)
        Button button2 = new Button("End app.Game");
        button2.setOnAction(e -> {
            if(game.finalScore() > highScore) {
                window.setScene(scene3);
            }
            else{

                window.setScene(scene4);
            }
        });
        gamegrid.setConstraints(button2, 12, 31);

        // Rock button
        Button rockbutton = new Button("Rock");

        rockbutton.setOnAction(e -> {
                    int result = game.play(1); // choice: 1 = rock, 2 = paper, 3 = scissors
                    updateGameLabels(game, winslabel,losslabel,tielabel,totallabel);
                    effectSelect(result);
                }
        );
        gamegrid.setConstraints(rockbutton, 9, 30);

        // Paper button
        Button paperbutton = new Button("Paper");

        paperbutton.setOnAction(e -> {
                    int result = game.play(2); // choice: 1 = rock, 2 = paper, 3 = scissors
                    updateGameLabels(game, winslabel,losslabel,tielabel,totallabel);
                    effectSelect(result);
                }
        );
        gamegrid.setConstraints(paperbutton, 12, 30);

        // Scissor button
        Button scissorbutton = new Button("Scissor");

        scissorbutton.setOnAction(e -> {
                    int result = game.play(3); // choice: 1 = rock, 2 = paper, 3 = scissors
                    updateGameLabels(game, winslabel,losslabel,tielabel,totallabel);
                    effectSelect(result);
                }
        );
        gamegrid.setConstraints(scissorbutton, 13, 30);

        gamegrid.getChildren().addAll(button2, rockbutton, paperbutton, scissorbutton, winslabel,
                losslabel, tielabel, totallabel);
        scene2 = new Scene(gamegrid, 1080, 960);



        // Layout 3 - To highscore submit

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Name Label
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 1);

        // Name input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);
        nameInput.setPromptText("Your name here");

        // Submitbutton - submit highscore
        Button submitbutton = new Button("Submit score");
        submitbutton.setOnAction(e -> {
            name = nameInput.getText();
            highScoreManager.setNewHighScore(name, game.finalScore());
            loadHighScore();
            updateHsLabel(highscoretitle);
            window.setScene(scene4);
        });
        GridPane.setConstraints(submitbutton, 1, 2);

        //title lable
        Label label2 = new Label("Enter your name to enter the highscore list");
        GridPane.setConstraints(label2, 0, 0);

        grid.getChildren().addAll(label2,nameLabel, nameInput, submitbutton);
        scene3 = new Scene(grid, 1080, 960);




        //Layout 4 - Highscore display

        GridPane highscoregrid = new GridPane();
        highscoregrid.setPadding(new Insets(10,10,10,10));
        highscoregrid.setVgap(8);
        highscoregrid.setHgap(10);

        // menubutton - back to menu
        Button menubutton = new Button("Back to main menu");
        menubutton.setOnAction(e -> {
            game.clearStats();
            window.setScene(scene1);
            player.play();
            winslabel.setText("Wins: " + Game.getWins());
            losslabel.setText("Losses: " + Game.getLosses());
            tielabel.setText("Ties: " + Game.getTies());
            totallabel.setText("Total Games Played: " + Game.getTotalgames());
        });
        GridPane.setConstraints(menubutton, 1, 1);

        // restartbutton - play again/ restart
        Button restartbutton = new Button("Restart");
        restartbutton.setOnAction(e -> {
            game.clearStats();
            window.setScene(scene2);

            winslabel.setText("Wins: " + Game.getWins());
            losslabel.setText("Losses: " + Game.getLosses());
            tielabel.setText("Ties: " + Game.getTies());
            totallabel.setText("Total Games Played: " + Game.getTotalgames());
        });
        GridPane.setConstraints(restartbutton, 2, 1);

        //highscore title lable
        highscoretitle = new Label("The top score is held by " + highScoreName + " with a score of " + highScore + ". (Wins - Losses)");
        GridPane.setConstraints(highscoretitle, 1, 2);

        highscoregrid.getChildren().addAll(menubutton, highscoretitle, restartbutton);
        scene4 = new Scene(highscoregrid, 1080, 960);




        // startup
        primaryStage.setScene(scene1); // set the main scene on stage
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Loads the highscore from a save file. If not existent, create a save file.
     */
    private void loadHighScore() {
        highScore = highScoreManager.getHighScore();
        highScoreName = highScoreManager.getHighScoreName();
    }

    private void updateHsLabel(Label label){
        label.setText("The top score is held by " + highScoreName + " with a score of " + highScore + ". (Wins - Losses)");
    }

    /**
     * Updates the labels displayed during a game.
     *
     * @param game instance
     * @param winslabel
     * @param losslabel
     * @param tielabel
     * @param totallabel
     */
    private void updateGameLabels(Game game, Label winslabel,Label losslabel,Label tielabel,Label totallabel){
        winslabel.setText("Wins: " + game.getWins());
        losslabel.setText("Losses: " + game.getLosses());
        tielabel.setText("Ties: " + game.getTies());
        totallabel.setText("Total Games Played: " + game.getTotalgames());
    }

    /**
     * Method to call upon closing the program.
     */
    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Warning!", "Are you sure you want to exit?");

        if(answer){
            Platform.exit();
        }
    }

    /**
     * Selects a random win sound to play.
     */
    private void playCheer(){
        String cheers[] = {"app/Sounds/ayayay.wav", "app/Sounds/ole.mp3"};
        Random random = new Random();
        playSound(cheers[random.nextInt(cheers.length)]);
    }

    /**
     * Selects a random loss sound to play.
     */
    private void playBoo(){
        String boos[] = {"app/Sounds/nonono.aif", "app/Sounds/no.wav"};
        Random random = new Random();
        playSound(boos[random.nextInt(boos.length)]);
    }

    /**
     * Selects a random draw sound to play.
     */
    private void playDraw(){
        String draws[] = {"app/Sounds/doitagain.wav"};
        Random random = new Random();
        playSound(draws[random.nextInt(draws.length)]);
    }

    /**
     * Plays a sound with a given path.
     *
     * @param path of sound to be played
     */
    private void playSound(String path){
        Media media = new Media(this.getClass().getClassLoader().getResource(path).toExternalForm().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
        player.setVolume(0.5);
    }

    /**
     * Calls on a win,lose,tie sound effect based on the result of a play.
     *
     * @param result (3:win 4:loss 5:tie)
     */
    private void effectSelect(int result){
        if(result == 3){
            playCheer();
        }
        else if(result == 4){
            playBoo();
        }
        else {
            playDraw();
        }
    }


}