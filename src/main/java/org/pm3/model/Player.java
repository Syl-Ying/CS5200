package org.pm3.model;


public class Player {
    private int playerID;  
    private String playerName;
    private String playerEmail;

    // Constructor (no need to include playerID, as it will be auto-generated)
    public Player(String playerName, String playerEmail) {
        this.playerName = playerName;
        this.playerEmail = playerEmail;
    }
    
    // No-argument constructor
    public Player() {
        // You can set default values here if necessary
    }
    
    // Constructor with playerID (for when fetching from DB)
    public Player(int playerID, String playerName, String playerEmail) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerEmail = playerEmail;
    }

    // Getters and Setters
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }


}
