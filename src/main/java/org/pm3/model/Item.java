package org.pm3.model;

public class Item {
  private int itemID;
  private String itemName;
  private int itemMaxStackSize;
  private SlotType slotType;
  private int itemVendorPrice;
  private boolean isAbleToSell;
  private String itemCategory;

  public Item(int itemID, String itemName, int itemMaxStackSize, SlotType slotType,
      int itemVendorPrice, boolean isAbleToSell, String itemCategory) {
    this.itemID = itemID;
    this.itemName = itemName;
    this.itemMaxStackSize = itemMaxStackSize;
    this.slotType = slotType;
    this.itemVendorPrice = itemVendorPrice;
    this.isAbleToSell = isAbleToSell;
    this.itemCategory = itemCategory;
  }

  public Item(String itemName, int itemMaxStackSize, SlotType slotType, int itemVendorPrice,
      boolean isAbleToSell, String itemCategory) {
    this.itemName = itemName;
    this.itemMaxStackSize = itemMaxStackSize;
    this.slotType = slotType;
    this.itemVendorPrice = itemVendorPrice;
    this.isAbleToSell = isAbleToSell;
    this.itemCategory = itemCategory;
  }

  public int getItemID() {
    return itemID;
  }

  public void setItemID(int itemID) {
    this.itemID = itemID;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public int getItemMaxStackSize() {
    return itemMaxStackSize;
  }

  public void setItemMaxStackSize(int itemMaxStackSize) {
    this.itemMaxStackSize = itemMaxStackSize;
  }

  public SlotType getSlotType() {
    return slotType;
  }

  public void setSlotType(SlotType slotType) {
    this.slotType = slotType;
  }

  public int getItemVendorPrice() {
    return itemVendorPrice;
  }

  public void setItemVendorPrice(int itemVendorPrice) {
    this.itemVendorPrice = itemVendorPrice;
  }

  public boolean isAbleToSell() {
    return isAbleToSell;
  }

  public void setAbleToSell(boolean ableToSell) {
    isAbleToSell = ableToSell;
  }

  public String getItemCategory() {
    return itemCategory;
  }

  public void setItemCategory(String itemCategory) {
    this.itemCategory = itemCategory;
  }
}
