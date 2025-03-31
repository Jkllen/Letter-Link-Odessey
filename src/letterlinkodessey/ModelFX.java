package letterlinkodessey;

import java.util.ArrayList;
import java.util.List;

public class ModelFX{
    private List<DialogueEntry> dialogues;
    private int currentDialogueIndex;
    
    public ModelFX(){
        dialogues = new ArrayList<>();
        dialogues.add(new DialogueEntry("Welcome to Letter Link Odessey!", "character.png", "scene1.png"));
        
        currentDialogueIndex = 0;
}
    public DialogueEntry getCurrentDialogue(){
        return dialogues.get(currentDialogueIndex);
    }
    public boolean nextDialogue(){
        if(currentDialogueIndex < dialogues.size()-1){
            currentDialogueIndex++;
            return true;
        }
        return false;
    } 
    public static class DialogueEntry{
        private String text;
        private String characterImg;
        private String backgroundImg;
        
        public DialogueEntry(String text, String characterImg, String backgroundImg){
            this.text = text;
            this.characterImg = characterImg;
            this.backgroundImg = backgroundImg;
        }
        
        public String getText() {
            return text;
        }
        
        public String getCharacterImg() {
            return characterImg;
        }
        
        public String getBackgroundImg() {
            return backgroundImg;
        }
    }
    
    public void startNewGame(){
        System.out.println("Starting new game...");
    }
    
    public void loadGame(){
        System.out.println("Loading saved game...");
    }
    
    public void openOptions(){
        System.out.println("Exiting game...");
        System.exit(0);
    }
}
