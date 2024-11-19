package org.pm3.model;

public class Inventory {
    protected Characters character;
    protected Item item;
    protected Integer currentItemQuantity;
    protected Integer inventorySlotNumber;

    public Inventory(Characters character, Item item, Integer currentItemQuantity, Integer inventorySlotNumber) {
        this.character = character;
        this.item = item;
        this.currentItemQuantity = currentItemQuantity;
        this.inventorySlotNumber = inventorySlotNumber;
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getCurrentItemQuantity() {
        return currentItemQuantity;
    }

    public void setCurrentItemQuantity(Integer currentItemQuantity) {
        this.currentItemQuantity = currentItemQuantity;
    }

    public Integer getInventorySlotNumber() {
        return inventorySlotNumber;
    }

    public void setInventorySlotNumber(Integer inventorySlotNumber) {
        this.inventorySlotNumber = inventorySlotNumber;
    }
}
