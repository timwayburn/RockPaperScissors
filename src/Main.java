import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.*;
import java.util.Random;


/**
 * Test Application :)
 *
 * Created by Tim on 21/04/16.
 */
public class Main extends Application {

    Scene scene1, scene2, scene3, scene4;
    Stage window;
    GridPane grid;
    Game game;
    Label highscoretitle;
    static int highScore;
    static String highScoreName;
    static String name;

    private String saveDataPath;
    private String fileName = "SaveData";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Highscore path
        try{
            saveDataPath = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        loadHighScore();


        Stage window = primaryStage; // primary stage
        window.setTitle("Rock Paper Scissors"); // Window title (top of screen)
        game = new Game();

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });



        // Layout 1 - Main menu

            //music for main menu
        String path = new File("Sounds/menumusic.mp3").getAbsolutePath();
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setStartTime(Duration.seconds(8));
        player.play();
        player.setVolume(0.08);

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

        MediaView mediaView = new MediaView(player);

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1, button3, mediaView);
        scene1 = new Scene(layout1, 1080, 960); // create the scene, set size of entire window



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
        Button button2 = new Button("End Game");
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
        gamegrid.setConstraints(rockbutton, 11, 30);

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
            setHighScore(game);
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

    private void loadHighScore() {
        try{
            File f = new File(saveDataPath, fileName);
            if(!f.isFile()){
                createSaveData();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String topscore = reader.readLine();
            String[] topScoreParts = topscore.split("\\s");
            highScore = Integer.parseInt(topScoreParts[1]);
            highScoreName = topScoreParts[0];
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void createSaveData(){
        try{
            File file = new File(saveDataPath, fileName);

            FileWriter output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("NOONE " + 000);
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void setHighScore(Game game){
        FileWriter output;
        try{
            File f = new File(saveDataPath,fileName);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            int thisscore = game.finalScore();

            if(thisscore >= highScore){
                writer.write(name + " " + thisscore);
            }
            else{
                writer.write(highScoreName + " " + highScore);
            }

            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void updateHsLabel(Label label){
        label.setText("The top score is held by " + highScoreName + " with a score of " + highScore + ". (Wins - Losses)");
    }

    private void updateGameLabels(Game game, Label winslabel,Label losslabel,Label tielabel,Label totallabel){
        winslabel.setText("Wins: " + game.getWins());
        losslabel.setText("Losses: " + game.getLosses());
        tielabel.setText("Ties: " + game.getTies());
        totallabel.setText("Total Games Played: " + game.getTotalgames());
    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Warning!", "Are you sure you want to exit?");

        if(answer){
            Platform.exit();
        }
    }

    private void playCheer(){
        String cheers[] = {"Sounds/Cheers/ayayay.wav","Sounds/Cheers/ole.mp3"};
        Random random = new Random();
        String path = new File(cheers[random.nextInt(cheers.length)]).getAbsolutePath();
        playSound(path);
    }

    private void playBoo(){
        String boos[] = {"Sounds/Boos/nonono.aif","Sounds/Boos/no.wav"};
        Random random = new Random();
        String path = new File(boos[random.nextInt(boos.length)]).getAbsolutePath();
        playSound(path);
    }

    private void playDraw(){
        String draws[] = {"Sounds/Draw/doitagain.wav"};
        Random random = new Random();
        String path = new File(draws[random.nextInt(draws.length)]).getAbsolutePath();
        playSound(path);
    }

    private void playSound(String path){
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.play();
        player.setVolume(0.5);
    }
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
