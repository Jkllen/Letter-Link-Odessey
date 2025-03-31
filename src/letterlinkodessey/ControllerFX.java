package letterlinkodessey;

import javafx.stage.Stage;

public class ControllerFX {
    private ModelFX model;
    private ViewFX view;
    private Stage stage;

    public ControllerFX(ModelFX model, ViewFX view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;

        // Initial UI update
        updateView();      
        
        view.getNewGameButton().setOnAction(event -> startNewGame());
    }

    private void updateView() {
        ModelFX.DialogueEntry current = model.getCurrentDialogue();
        if (current != null) {
            view.setDialogue(current.getText());

            // Ensure the paths exist before setting images
            if (current.getCharacterImg() != null && !current.getCharacterImg().isEmpty()) {
                view.setCharacterImage(current.getCharacterImg());
            }
            if (current.getBackgroundImg() != null && !current.getBackgroundImg().isEmpty()) {
                view.setBackgroundImage(current.getBackgroundImg());
            }
        }
    }
    
    private void startNewGame(){
        FirstSceneFX firstScene = new FirstSceneFX(stage, this);
        firstScene.display();
                
    }
}
