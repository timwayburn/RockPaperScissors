import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


/**
 *  The class for an Alert Box, like the one that appears when the player has won 10 times.
 *  Created by Tim Wayburn & Philippa Örnell on 21/05/16.
 */
public class AlertBox {

    /**
     * @param title of Alert Box
     * @param message to be displayed in Alert Box
     */
    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(250);
        window.setHeight(250);


        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background: #AA0000;");

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }


}
