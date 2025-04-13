package letterlinkodessey;

import java.util.List;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Chapter1FX {
    private Stage stage;
    private ControllerFX controller;
    private BackgroundManage backgroundManager;
    private CharacterManage characterManager;
    private Label dialogueLabel;
    private Label speakerLabel;
    private ModelFX model;
    private int currentDialogueIndex = 0;
    private StackPane layout;
    private boolean isAnimating = false;
    private String fullTextBuffer = "";
    private boolean choicesVisible = false;


    public Chapter1FX(Stage stage, ControllerFX controller, ModelFX model) {
        this.stage = stage;
        this.controller = controller;
        this.model = model;
        this.backgroundManager = new BackgroundManage();
        this.characterManager = new CharacterManage();
    }

    public void display() {
        bedroomScene();
    }

    private void bedroomScene() {
        String characterPath = controller.getCharacterImage();
        characterManager.setCharacterImage(characterPath);

        dialogueLabel = new Label("");
        dialogueLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        dialogueLabel.setTranslateY(150);
        dialogueLabel.setTranslateX(-10);
        dialogueLabel.setVisible(true); // Show dialogue immediately since name is already handled
        dialogueLabel.setWrapText(true);
        dialogueLabel.setMaxWidth(700);
        
        speakerLabel = new Label("");
        speakerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: gold; -fx-font-weight: bold;"
                + "-fx-background-color: rgba(0, 0, 0, 0.6);  -fx-background-radius: 10; -fx-padding: 6 12;");
        speakerLabel.setTranslateY(100);
        speakerLabel.setTranslateX(-350);
        speakerLabel.setVisible(true);
        
        layout = new StackPane();
        layout.setOnMouseClicked(e -> {
            if (!choicesVisible) {
                showNextDialogue();
            }
        });


        layout.getChildren().addAll(
            backgroundManager.getBackgroundImageView(),
            characterManager.getCharacterImageView(),
            speakerLabel,
            dialogueLabel
        );

        Scene bedroomScene = new Scene(layout, 800, 455);
        stage.setScene(bedroomScene);
        stage.setResizable(false);
        stage.show();

        startDialogue(); // Start dialogue immediately
    }

    private void startDialogue() {
        dialogueLabel.setVisible(true);
        dialogueLabel.setText("");

        DialogueEntry current = model.getCurrentDialogue(currentDialogueIndex);
        if (current == null) {
            return;
        }
        

        String playerName = model.getPlayerName();

        String speaker = current.getSpeaker().replace("{player}", playerName);
        String fullText = current.getText().replace("{player}", playerName);
        speakerLabel.setText(speaker);

        String characterImage = current.getCharacterImg();
        if (characterImage != null && !characterImage.isEmpty()) {
            characterManager.setCharacterImage("src/assets/characters/" + characterImage);
        }

        String backgroundImage = current.getBackgroundImg();
        if (backgroundImage != null && !backgroundImage.isEmpty()) {
            backgroundManager.setBackgroundImage("src/assets/backgrounds/" + backgroundImage);
        }

        animateText(dialogueLabel, fullText);
    }

    

    private void showNextDialogue() {
        if (isAnimating) {
            isAnimating = false;
            dialogueLabel.setText(fullTextBuffer);
            return;
        }

        if (currentDialogueIndex < model.getTotalDialogues()) {
            DialogueEntry current = model.getCurrentDialogue(currentDialogueIndex);
            if (current == null) {
                return;
            }

            String playerName = model.getPlayerName();
            String speaker = current.getSpeaker().replace("{player}", playerName);
            String fullText = current.getText().replace("{player}", playerName);
            speakerLabel.setText(speaker);

            // Load character
            String characterImage = current.getCharacterImg();
            if (characterImage != null && !characterImage.isEmpty()) {
                characterManager.setCharacterImage("src/assets/characters/" + characterImage);
            }

            // Load background
            String backgroundImage = current.getBackgroundImg();
            if (backgroundImage != null && !backgroundImage.isEmpty()) {
                backgroundManager.setBackgroundImage("src/assets/backgrounds/" + backgroundImage);
            }

            // Start animation
            dialogueLabel.setText("");
            animateText(dialogueLabel, fullText);

            // Handle branching
            int nextId = current.getNextDialogueId();
            if (nextId > 0) {
                currentDialogueIndex = model.getDialogueIndexById(nextId);
            } else {
                currentDialogueIndex++;
            }

            // Show choices if applicable
            if ("choice".equalsIgnoreCase(current.getType())) {
                displayChoices(current.getId());
                return;
            }

            // End of dialogues
            if (currentDialogueIndex >= model.getTotalDialogues()) {
                proceedToDiningTableScene();
            }
        }
    }



    private void animateText(Label label, String text) {
        isAnimating = true;
        fullTextBuffer = text;

        new Thread(() -> {
            try {
                for (int i = 0; i < text.length(); i++) {
                    int index = i;
                    if (!isAnimating) {
                        break; // If interrupted, stop the animation
                    }
                    String currentText = text.substring(0, index + 1);
                    Platform.runLater(() -> label.setText(currentText));
                    Thread.sleep(30); // Adjust speed as needed
                }
                Platform.runLater(() -> {
                    label.setText(text); //
                    isAnimating = false;
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    
    private void displayChoices(int dialogueId) {
        choicesVisible = true;
        List<ChoiceEntry> options = model.getChoicesForDialogue(dialogueId);
        VBox choiceBox = new VBox(15);
        choiceBox.setTranslateY(250); // Position nicely
        choiceBox.setStyle("");

        for (ChoiceEntry choice : options) {
            Button btn = new Button(choice.getChoiceText());

            btn.setOnAction(e -> {
                int nextId = choice.getNextDialogueId();
                int nextIndex = model.getDialogueIndexById(nextId);

                if (nextIndex != -1) {
                    currentDialogueIndex = nextIndex;

                    layout.getChildren().remove(choiceBox);
                    choicesVisible = false;
                    showNextDialogue(); 
                }
            });



            choiceBox.getChildren().add(btn);
        }

        layout.getChildren().add(choiceBox);
    }


    private void proceedToDiningTableScene() {
        diningTableScene();
    }

    private void diningTableScene() {
        backgroundManager.setBackgroundImage("src/assets/backgrounds/diningtable.png");

        dialogueLabel = new Label("");
        dialogueLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        dialogueLabel.setTranslateY(200);
        dialogueLabel.setVisible(true);

        String characterPath = controller.getCharacterImage();
        characterManager.setCharacterImage(characterPath);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(
            backgroundManager.getBackgroundImageView(),
            characterManager.getCharacterImageView(),
            dialogueLabel
        );

        Scene diningTableScene = new Scene(layout, 755, 455);
        stage.setScene(diningTableScene);
        stage.show();
    }
}
