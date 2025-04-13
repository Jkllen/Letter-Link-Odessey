package letterlinkodessey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelFX {
    private List<DialogueEntry> dialogues;
    private int currentDialogueIndex;
    private String playerName;

    public ModelFX() {
        dialogues = new ArrayList<>();
        currentDialogueIndex = 0;
        loadDialoguesFromDatabase();
    }

    public void loadDialoguesFromDatabase() {
        dialogues.clear();
        String sql = "SELECT id, speaker, text, character_image, background_image, sfx, type, next_dialogue_id FROM dialogues ORDER BY id";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                dialogues.add(new DialogueEntry(
                        rs.getInt("id"),
                        rs.getString("speaker"),
                        rs.getString("text"),
                        rs.getString("character_image"),
                        rs.getString("background_image"),
                        rs.getString("sfx"),
                        rs.getString("type"),
                        rs.getInt("next_dialogue_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Loaded dialogues:");
        for (DialogueEntry d : dialogues) {
            System.out.println("ID: " + d.getId() + " | " + d.getText());
        }

    }


    public void saveGame(int slot) {
        String sql = "REPLACE INTO saves (slot_id, player_name, dialogue_index) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, slot);
            stmt.setString(2, playerName);
            stmt.setInt(3, currentDialogueIndex);
            stmt.executeUpdate();
            System.out.println("Game saved to slot " + slot);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean loadGame(int slot) {
        String sql = "SELECT player_name, dialogue_index FROM saves WHERE slot_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, slot);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                playerName = rs.getString("player_name");
                currentDialogueIndex = rs.getInt("dialogue_index");
                System.out.println("Loaded game from slot " + slot);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void savePlayerNameToDatabase() {
    String sql = "INSERT INTO players (player_Name) VALUES (?)";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, playerName);
        stmt.executeUpdate();
        System.out.println("✅ Player name inserted: " + playerName);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public DialogueEntry getCurrentDialogue(int index) {
        if (index >= 0 && index < dialogues.size()) {
            return dialogues.get(index);
        }
        return null;
    }

    public int getTotalDialogues() {
        return dialogues.size();
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void startNewGame() {
        currentDialogueIndex = 0;
    }

    public String getBackgroundImagePathByName(String backgroundName) {
        String sql = "SELECT image_path FROM backgrounds WHERE name = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, backgroundName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("image_path");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChoiceEntry> getChoicesForDialogue(int dialogueId) {
        List<ChoiceEntry> choices = new ArrayList<>();
        String sql = "SELECT choice_text, next_dialogue_id FROM choices WHERE dialogue_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dialogueId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                choices.add(new ChoiceEntry(rs.getString("choice_text"), rs.getInt("next_dialogue_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return choices;
    }

    public int getDialogueIndexById(int dialogueId) {
        for (int i = 0; i < dialogues.size(); i++) {
            if (dialogues.get(i).getId() == dialogueId) {
                return i;
            }
        }
        return -1;
    }


    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.");
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mygame?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "password");
    }
}
