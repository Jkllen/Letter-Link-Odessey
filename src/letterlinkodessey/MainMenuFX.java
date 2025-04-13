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
    private Scene scene;

    public MainMenuFX(Stage primaryStage) {
        newGame = new Button("New Game");
        loadGame = new Button("Load Game");
        options = new Button("Options");
        exit = new Button("Exit");

        backgroundImageView = new ImageView(new Image("file:src/assets/backgrounds/mainmenu.png"));
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(455);
        backgroundImageView.setPreserveRatio(true);

        // Style buttons
        styleButton(newGame, "Irish Grover");
        styleButton(loadGame, "Irish Grover");
        styleButton(options, "Irish Grover");
        styleButton(exit, "Irish Grover");

        // Set button positions
        setPosition(newGame, 70, 90);
        setPosition(loadGame, 70, 170);
        setPosition(options, 70, 250);
        setPosition(exit, 70, 330);

        // Drop shadow
        DropShadow glow = new DropShadow(20, Color.GOLD);
        addHoverEffect(newGame, glow);
        addHoverEffect(loadGame, glow);
        addHoverEffect(options, glow);
        addHoverEffect(exit, glow);

        root = new Pane();
        root.getChildren().addAll(backgroundImageView, newGame, loadGame, options, exit);

        scene = new Scene(root, 800, 455);
    }

    // Helper Methods
    private void styleButton(Button button, String fontFamily) {
        button.setStyle("-fx-font-size: 24px; -fx-font-family: '" + fontFamily + "'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-font-size: 24px; -fx-font-family: '" + fontFamily + "'; -fx-text-fill: gold; -fx-background-color: transparent; -fx-font-weight: bold;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-font-size: 24px; -fx-font-family: '" + fontFamily + "'; -fx-text-fill: white; -fx-background-color: transparent; -fx-font-weight: bold;"));
    }

    private void setPosition(Button button, int x, int y) {
        button.setPrefSize(200, 50);
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    private void addHoverEffect(Button button, DropShadow glow) {
        button.setOnMouseEntered(e -> button.setEffect(glow));
        button.setOnMouseExited(e -> button.setEffect(null));
    }

    // Public getters
    public Scene getScene() {
        return scene;
    }

    public Button getNewGameButton() {
        return newGame;
    }

    public Button getLoadGameButton() {
        return loadGame;
    }

    public Pane getRootPane() {
        return root;
    }

    public void setBackgroundImage(String imagePath) {
        backgroundImageView.setImage(new Image("file:src/assets/backgrounds/mainmenu.png"));
    }
}
