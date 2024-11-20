package pm3.model;


public class GearWeaponAttributeBonus {
    private int itemID;
    private int attributeID;
    private int bonuesValue;
    
    public GearWeaponAttributeBonus(int itemID, int attributeID, int bonuesValue) {
        this.itemID = itemID;
        this.attributeID = attributeID;
        this.bonuesValue = bonuesValue;
    }
    
    // Getters and Setters
	public int getItemID() {
		return itemID;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public int getBonusValue() {
		return bonuesValue;
	}

	public void setBonusValue(int bonuesValue) {
		this.bonuesValue = bonuesValue;
	}
    
}
