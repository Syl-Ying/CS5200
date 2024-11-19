package org.pm3.model;

public class CharacterAttribute {
	private int attributeID;
    private String attributeName;
    
    public CharacterAttribute(int attributeID, String attributeName) {
        this.attributeID = attributeID;
        this.attributeName = attributeName;
    }
    
    public CharacterAttribute(String attributeName) {
        this.attributeName = attributeName;
    }
    
	public int getAttributeID() {
		return attributeID;
	}
	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
    
    

}
