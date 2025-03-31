package letterlinkodessey;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FirstSceneFX {
    private Stage stage;
    private ControllerFX controller;

    public FirstSceneFX(Stage stage, ControllerFX controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void display() {
        Label sceneText = new Label("Welcome to the first scene of the game!");
        StackPane layout = new StackPane(sceneText);
        Scene firstScene = new Scene(layout, 800, 600);

        stage.setScene(firstScene);
        stage.show();
    }
}
