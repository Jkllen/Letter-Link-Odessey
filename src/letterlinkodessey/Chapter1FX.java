package letterlinkodessey;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Chapter1FX {
    private Stage stage;
    private ControllerFX controller;
    private BackgroundManage backgroundManager;
    private CharacterManage characterManager;
    private Label dialogueLabel;
    private TextField nameField;
    private ModelFX model;
    
    public Chapter1FX(Stage stage, ControllerFX controller, ModelFX model) {
        this.stage = stage;
        this.controller = controller;
        this.model = model; //Connection for the database in the Model class
        this.backgroundManager = new BackgroundManage();
        this.characterManager = new CharacterManage();
    }

    public void display() {
        // Starting with Bedroom Scene
        bedroomScene();
    }

    private void bedroomScene() {
        // Set background specific to Bedroom scene
        backgroundManager.setBackgroundImage("src/assets/backgrounds/bedroom.png");

        // Set character image from the model/controller
        String characterPath = controller.getCharacterImage();
        characterManager.setCharacterImage(characterPath);

        // Dialogue
        dialogueLabel = new Label("You wake up in your bedroom...");
        dialogueLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        dialogueLabel.setTranslateY(200);

        // Name Entry (at the top of the screen)
        nameField = new TextField();
        nameField.setMaxWidth(200);
        nameField.setPromptText("Enter your name...");
        nameField.setTranslateY(-250); // Place at the top

        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(e -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty()) {
                controller.savePlayerName(playerName);  // Save name to model
                model.savePlayerNameToDatabase(playerName);  // Save to the database
                proceedToDiningTableScene();  // Move to the next scene
            }
        });

        // Layout
        StackPane layout = new StackPane();
        layout.getChildren().addAll(
            backgroundManager.getBackgroundImageView(),
            characterManager.getCharacterImageView(),
            dialogueLabel,
            nameField,
            submitBtn
        );

        // Scene setup
        Scene bedroomScene = new Scene(layout, 800, 600);
        stage.setScene(bedroomScene);
        stage.show();
    }

    private void proceedToDiningTableScene() {
        // Transition to the Dining Table scene
        diningTableScene();
    }

    private void diningTableScene() {
        // Set background specific to Dining Table scene
        backgroundManager.setBackgroundImage("src/assets/backgrounds/diningtable.png");

        // Dialogue
        dialogueLabel.setText("You head downstairs to the dining table...");
        
        // Set character image from the model/controller
        String characterPath = controller.getCharacterImage();
        characterManager.setCharacterImage(characterPath);

        // Layout
        StackPane layout = new StackPane();
        layout.getChildren().addAll(
            backgroundManager.getBackgroundImageView(),
            characterManager.getCharacterImageView(),
            dialogueLabel
        );

        // Scene setup
        Scene diningTableScene = new Scene(layout, 800, 600);
        stage.setScene(diningTableScene);
        stage.show();
    }
}