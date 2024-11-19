package org.pm3.model;

public class ConsumableAttributeBonus {
	private int bonusID;
    private int consumableID;
    private int attributeID;
    private double bonusPercentage;
    private int bonusCap;
    
    public ConsumableAttributeBonus(int bonusID, int consumableID, int attributeID, double bonusPercentage, int bonusCap) {
    	this.bonusID = bonusID;
    	this.consumableID = consumableID;
        this.attributeID = attributeID;
        this.bonusPercentage = bonusPercentage;
        this.bonusCap = bonusCap;
    }
	
	public ConsumableAttributeBonus(int consumableID, int attributeID, double bonusPercentage, int bonusCap) {
        this.consumableID = consumableID;
        this.attributeID = attributeID;
        this.bonusPercentage = bonusPercentage;
        this.bonusCap = bonusCap;
    }
	
	// Getters and Setters
	public int getBonusID() {
        return bonusID;
    }

    public void setBonusID(int bonusID) {
        this.bonusID = bonusID;
    }

	public int getConsumableID() {
		return consumableID;
	}

	public void setConsumableID(int consumableID) {
		this.consumableID = consumableID;
	}

	public int getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(int attributeID) {
		this.attributeID = attributeID;
	}

	public double getBonusPercentage() {
		return bonusPercentage;
	}

	public void setBonusPercentage(double bonusPercentage) {
		this.bonusPercentage = bonusPercentage;
	}

	public int getBonusCap() {
		return bonusCap;
	}

	public void setBonusCap(int bonusCap) {
		this.bonusCap = bonusCap;
	}

}
