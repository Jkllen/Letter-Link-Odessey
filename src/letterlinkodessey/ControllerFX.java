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

        //Initial UI update
        updateView();      
        
        view.getNewGameButton().setOnAction(event -> showWakeUpScene());
        view.getRootPane().setOnMouseClicked(event -> nextDialogue()); 
    }

    private void updateView() {
    ModelFX.DialogueEntry current = model.getCurrentDialogue();
    if (current != null) {
        // Only update the background image if available
        if (current.getBackgroundImg() != null && !current.getBackgroundImg().isEmpty()) {
            view.setBackgroundImage(current.getBackgroundImg());
        }
    }
}

    public void nextDialogue(){
        dialogueIndex++;
        if(dialogueIndex < model.getDialogueSize()){
            updateView();
        } else {
            requestPlayerName();
        }
    }
    
    public void requestPlayerName(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter your Name");
        dialog.setHeaderText("Please enter your name to begin!");
        dialog.setContentText("Name: ");
        
        Optional<String> result = dialog.showAndWait(); 
        result.ifPresent(name -> {
            model.setPlayerName(name);
            System.out.println("Player Name: " + name);
        });
    }
    

    public String getCharacterImage() {
        // Logic to fetch character image path based on game state
        return "file:src/assets/characters/protagonist.png";
    }

    public String getCurrentDialogue() {
        // Logic to fetch current dialogue text based on game state
        return model.getCurrentDialogue().getText();
    }

    public void savePlayerName(String name) {
        model.setPlayerName(name);
    }

    public String getPlayerName() {
        return model.getPlayerName();
    }

    public void showWakeUpScene() {
    Chapter1FX bedroomScene = new Chapter1FX(stage, this, model);
    bedroomScene.display();
    }
}

