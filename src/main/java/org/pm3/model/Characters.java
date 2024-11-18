package org.pm3.model;

public class Characters {
    private int characterID;
    private Player player; 
    private String characterFirstName;
    private String characterLastName;
    private int currentMaxHP;

    // Constructor for creating a new Character (without characterID)
    public Characters(Player player, String characterFirstName, String characterLastName, int currentMaxHP) {
        this.player = player;
        this.characterFirstName = characterFirstName;
        this.characterLastName = characterLastName;
        this.currentMaxHP = currentMaxHP;
    }

    // Constructor for retrieving an existing Character (with characterID)
    public Characters(int characterID, Player player, String characterFirstName, String characterLastName, int currentMaxHP) {
        this.characterID = characterID;
        this.player = player;
        this.characterFirstName = characterFirstName;
        this.characterLastName = characterLastName;
        this.currentMaxHP = currentMaxHP;
    }


    public Characters(int characterID) {
        this.characterID = characterID;
    }

	// Getters and Setters
    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getCharacterFirstName() {
        return characterFirstName;
    }

    public void setCharacterFirstName(String characterFirstName) {
        this.characterFirstName = characterFirstName;
    }

    public String getCharacterLastName() {
        return characterLastName;
    }

    public void setCharacterLastName(String characterLastName) {
        this.characterLastName = characterLastName;
    }

    public int getCurrentMaxHP() {
        return currentMaxHP;
    }

    public void setCurrentMaxHP(int currentMaxHP) {
        this.currentMaxHP = currentMaxHP;
    }


}
