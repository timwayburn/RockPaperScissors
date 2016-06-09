package app;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * The class for a Confirm Box, such as the one that appears when exiting the game.
 *
 * Created by Tim Wayburn & Philippa Örnell on 21/04/16.
 */
public class ConfirmBox {

    static boolean answer;

    /**
     * @param title of Confirm Box
     * @param message to be displayed in Confirm Box
     * @return true if 'yes' selected, false otherwise.
     */
    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label1 = new Label();
        label1.setText(message);

        // Yes and no buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e ->{
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
