package letterlinkodessey;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewFX {
    private Label dialogueLabel;
    private Button newGame, loadGame, options, exit;
    private ImageView characterImageView;
    private ImageView backgroundImageView;
    private Pane root; // Use Pane for absolute positioning

    public ViewFX(Stage primaryStage) {
        dialogueLabel = new Label("Welcome to Letter Link Odyssey!");
        newGame = new Button("New Game");
        loadGame = new Button("Load Game");
        options = new Button("Options");
        exit = new Button("Exit");

        // Background Image
        backgroundImageView = new ImageView(new Image("file:src/assets/backgrounds/mainmenu.png"));
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setPreserveRatio(true);

        // Character Image
        characterImageView = new ImageView();
        characterImageView.setFitWidth(200);
        characterImageView.setFitHeight(300);
        characterImageView.setPreserveRatio(true);

        // Button Styling
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
        
        newGame.setPrefSize(200, 50); // Adjust size to fit "New Game" text
        newGame.setLayoutX(70);  // X position (Adjust to fit text position)
        newGame.setLayoutY(90);  // Y position (Adjust to fit text position)
        
        loadGame.setPrefSize(200, 50); // Adjust size to fit "New Game" text
        loadGame.setLayoutX(70);  // X position (Adjust to fit text position)
        loadGame.setLayoutY(170);  // Y position (Adjust to fit text position)
        
        options.setPrefSize(200, 50); // Adjust size to fit "New Game" text
        options.setLayoutX(70);  // X position (Adjust to fit text position)
        options.setLayoutY(250);  // Y position (Adjust to fit text position)
        
        exit.setPrefSize(200, 50); // Adjust size to fit "New Game" text
        exit.setLayoutX(70);  // X position (Adjust to fit text position)
        exit.setLayoutY(330);  // Y position (Adjust to fit text position)
        
        DropShadow glow = new DropShadow(20, Color.GOLD);
        newGame.setOnMouseEntered(e -> newGame.setEffect(glow));
        newGame.setOnMouseExited(e -> newGame.setEffect(null));
        loadGame.setOnMouseEntered(e -> loadGame.setEffect(glow));
        loadGame.setOnMouseExited(e -> loadGame.setEffect(null));
        
        
        // Use Pane for precise positioning
        root = new Pane();
        root.getChildren().addAll(backgroundImageView, characterImageView, newGame, loadGame, options, exit);

        Scene scene = new Scene(root, 800, 455);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Letter Link Odyssey");
        primaryStage.show();
    }

    public void setDialogue(String text) {
        dialogueLabel.setText(text);
    }

    public void setCharacterImage(String imagePath) {
        characterImageView.setImage(new Image("file:src/tip/edu/assets/characters/" + imagePath));
    }

    public void setBackgroundImage(String imagePath) {
        backgroundImageView.setImage(new Image("file:src/assets/backgrounds/template1.png"));
    }

    public Button getNewGameButton() {
        return newGame;
    }
}
