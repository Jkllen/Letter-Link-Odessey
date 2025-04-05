package letterlinkodessey;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NameEntryFX {
    private Stage stage;
    private ControllerFX controller;

    public NameEntryFX(Stage stage, ControllerFX controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void display() {
        Label instruction = new Label("Please enter your name:");
        instruction.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        TextField nameField = new TextField();
        nameField.setMaxWidth(200);

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                controller.savePlayerName(playerName);
            }
        });

        VBox layout = new VBox(15, instruction, nameField, submitBtn);
        layout.setStyle("-fx-background-color: #000000;");
        layout.setAlignment(Pos.CENTER);

        Scene nameScene = new Scene(layout, 800, 600);
        stage.setScene(nameScene);
        stage.show();
    }
}
