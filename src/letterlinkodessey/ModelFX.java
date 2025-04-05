package letterlinkodessey;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModelFX {
    private List<DialogueEntry> dialogues;
    private int currentDialogueIndex;
    private String playerName;

    // Constructor
    public ModelFX() {
        dialogues = new ArrayList<>();
        currentDialogueIndex = 0;
    }

    // Adds a new dialogue entry
    public void addDialogue(String text, String characterImg, String backgroundImg) {
        dialogues.add(new DialogueEntry(text, characterImg, backgroundImg));
    }

    // Get the current dialogue entry
    public DialogueEntry getCurrentDialogue() {
        if (currentDialogueIndex < dialogues.size()) {
            return dialogues.get(currentDialogueIndex);
        }
        return null; // Or return a default DialogueEntry if needed
    }

    // Go to the next dialogue
    public boolean nextDialogue() {
        if (currentDialogueIndex < dialogues.size() - 1) {
            currentDialogueIndex++;
            return true;
        }
        return false;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void startNewGame() {
        System.out.println("Starting new game...");
        currentDialogueIndex = 0;
    }

    public void loadGame() {
        System.out.println("Loading saved game...");
    }

    public void openOptions() {
        System.out.println("Exiting game...");
        System.exit(0);
    }

    public int getDialogueSize() {
        return dialogues.size();
    }

    public class DialogueEntry {
        private String text;
        private String characterImg;
        private String backgroundImg;

        public DialogueEntry(String text, String characterImg, String backgroundImg) {
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
    
    // Method to save player name to the database
    public void savePlayerNameToDatabase(String playerName) {
        // Database connection details
        final String URL = "jdbc:mysql://localhost:3306/mygame?zeroDateTimeBehavior=CONVERT_TO_NULL";
        final String USER = "root";
        final String PASSWORD = "password";
        String sql = "INSERT INTO players (player_Name) VALUES (?)"; // Adjusted column name

        // Get a connection to the database
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the player name in the prepared statement
            stmt.setString(1, playerName);

            // Execute the insert query
            stmt.executeUpdate();
            System.out.println("Player name inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to establish a connection to the database
    private static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.");
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mygame", "root", "password");
    }
}
