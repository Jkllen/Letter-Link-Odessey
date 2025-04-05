package letterlinkodessey;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainMenuFX {
    private Button newGame, loadGame, options, exit;
    private ImageView backgroundImageView;
    private Pane root;

    public MainMenuFX(Stage primaryStage) {
        newGame = new Button("New Game");
        loadGame = new Button("Load Game");
        options = new Button("Options");
        exit = new Button("Exit");

        // Initialize the background image view for template1.png
        backgroundImageView = new ImageView(new Image("file:src/assets/backgrounds/template1.png"));
        backgroundImageView.setFitWidth(800);  // Adjust to the size of your scene
        backgroundImageView.setFitHeight(455); // Adjust to the size of your scene
        backgroundImageView.setPreserveRatio(true);

        // Button Styling and Effects
        newGame.setStyle("-fx-font-size: 24px; -fx-font-family: 'Irish Grover'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;");
        newGame.setOnMouseEntered(e -> newGame.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: gold; -fx-background-color: transparent; -fx-font-weight: bold;"));
        newGame.setOnMouseExited(e -> newGame.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;"));

        loadGame.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;");
        loadGame.setOnMouseEntered(e -> loadGame.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: gold; -fx-background-color: transparent; -fx-font-weight: bold;"));
        loadGame.setOnMouseExited(e -> loadGame.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;"));

        options.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;");
        options.setOnMouseEntered(e -> options.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: gold; -fx-background-color: transparent; -fx-font-weight: bold;"));
        options.setOnMouseExited(e -> options.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;"));

        exit.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;");
        exit.setOnMouseEntered(e -> exit.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: gold; -fx-background-color: transparent; -fx-font-weight: bold;"));
        exit.setOnMouseExited(e -> exit.setStyle("-fx-font-size: 24px; -fx-font-family: 'Georgia'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;"));

        newGame.setPrefSize(200, 50);
        newGame.setLayoutX(70);
        newGame.setLayoutY(90);

        loadGame.setPrefSize(200, 50);
        loadGame.setLayoutX(70);
        loadGame.setLayoutY(170);

        options.setPrefSize(200, 50);
        options.setLayoutX(70);
        options.setLayoutY(250);

        exit.setPrefSize(200, 50);
        exit.setLayoutX(70);
        exit.setLayoutY(330);

        // Add drop shadow effect to buttons
        DropShadow glow = new DropShadow(20, Color.GOLD);
        newGame.setOnMouseEntered(e -> newGame.setEffect(glow));
        newGame.setOnMouseExited(e -> newGame.setEffect(null));

        loadGame.setOnMouseEntered(e -> loadGame.setEffect(glow));
        loadGame.setOnMouseExited(e -> loadGame.setEffect(null));

        options.setOnMouseEntered(e -> options.setEffect(glow));
        options.setOnMouseExited(e -> options.setEffect(null));

        exit.setOnMouseEntered(e -> exit.setEffect(glow));
        exit.setOnMouseExited(e -> exit.setEffect(null));

        // Create root pane and add the background image and buttons
        root = new Pane();
        root.getChildren().addAll(backgroundImageView, newGame, loadGame, options, exit);

        // Create the scene and set it on the primary stage
        Scene scene = new Scene(root, 800, 455);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Letter Link Odyssey");
        primaryStage.show();
    }

    public void setBackgroundImage(String imagePath) {
        // Only set the background image to template1.png
        backgroundImageView.setImage(new Image("file:src/assets/backgrounds/template1.png"));
    }

    public Button getNewGameButton() {
        return newGame;
    }

    public Pane getRootPane() {
        return root;
    }
}
