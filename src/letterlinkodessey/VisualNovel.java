//Main runnable
package letterlinkodessey;

import javafx.application.Application;
import javafx.stage.Stage;

public class VisualNovel extends Application {
    private ModelFX model;
    private MainMenuFX view;
    private ControllerFX controller;

    @Override
    public void start(Stage primaryStage) {
        //Initialize Model, View, and Controller
        model = new ModelFX();
        view = new MainMenuFX(primaryStage);
        controller = new ControllerFX(model, view, primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
