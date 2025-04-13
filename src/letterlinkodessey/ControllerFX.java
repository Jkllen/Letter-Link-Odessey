package letterlinkodessey;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class ControllerFX {
    private ModelFX model;
    private MainMenuFX view;
    private Stage stage;
    private int dialogueIndex = 0;

    public ControllerFX(ModelFX model, MainMenuFX view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;

        updateView();      

        view.getNewGameButton().setOnAction(event -> showNameEntry());

        view.getLoadGameButton().setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Load Game");
            dialog.setHeaderText("Enter save slot number (e.g., 1-3):");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(slotStr -> {
                try {
                    int slot = Integer.parseInt(slotStr);
                    if (model.loadGame(slot)) {
                        showWakeUpScene();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid slot input.");
                }
            });
        });

        view.getRootPane().setOnMouseClicked(event -> nextDialogue());
    }

    private void updateView() {
        DialogueEntry current = model.getCurrentDialogue(dialogueIndex);
        if (current != null && current.getBackgroundImg() != null && !current.getBackgroundImg().isEmpty()) {
            view.setBackgroundImage(current.getBackgroundImg());
        }
    }

    public void nextDialogue() {
        dialogueIndex++;
        if (dialogueIndex < model.getTotalDialogues()) {
            updateView();
        } else {
            showWakeUpScene(); //
        }
    }

    public String getCharacterImage() {
        return "file:src/assets/characters/protagonist.png";
    }

    public void savePlayerName(String name) {
        model.setPlayerName(name);
        model.savePlayerNameToDatabase(); 
        showWakeUpScene();
    }

    public String getPlayerName() {
        return model.getPlayerName();
    }

    public void showNameEntry() {
        NameEntryFX nameEntry = new NameEntryFX(stage, this);
        nameEntry.display();
    }

    public void showWakeUpScene() {
        Chapter1FX bedroomScene = new Chapter1FX(stage, this, model);
        bedroomScene.display();
    }
    
    public ModelFX getModel() {
    return model;
}

}
