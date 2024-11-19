package org.pm3.model;

public class CharacterAttributeValues {
	private int characterID;
    private int attributeID;
    private int attributeValue;
    
    public CharacterAttributeValues(int characterID, int attributeID, int attributeValue) {
        this.characterID = characterID;
        this.attributeID = attributeID;
        this.attributeValue = attributeValue;
    }
    
	// Getters and Setters
	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public int getAttributeValue() {
		return attributeValue;
	}
	
	public void setAttributeValue(int attributeValue) {
		this.attributeValue = attributeValue;
	}


}
