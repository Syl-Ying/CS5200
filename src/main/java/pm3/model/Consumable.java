package pm3.model;

public class Consumable extends Item{
  private int consumableItemLevel;
  private int consumableDescription;

  public Consumable(int itemID, String itemName, int itemMaxStackSize, SlotType slotType,
      int itemVendorPrice, boolean isAbleToSell, String itemCategory, int consumableItemLevel,
      int consumableDescription) {
    super(itemID, itemName, itemMaxStackSize, slotType, itemVendorPrice, isAbleToSell,
        itemCategory);
    this.consumableItemLevel = consumableItemLevel;
    this.consumableDescription = consumableDescription;
  }

  public Consumable(String itemName, int itemMaxStackSize, SlotType slotType, int itemVendorPrice,
      boolean isAbleToSell, String itemCategory, int consumableItemLevel,
      int consumableDescription) {
    super(itemName, itemMaxStackSize, slotType, itemVendorPrice, isAbleToSell, itemCategory);
    this.consumableItemLevel = consumableItemLevel;
    this.consumableDescription = consumableDescription;
  }

  public int getConsumableItemLevel() {
    return consumableItemLevel;
  }

  public void setConsumableItemLevel(int consumableItemLevel) {
    this.consumableItemLevel = consumableItemLevel;
  }

  public int getConsumableDescription() {
    return consumableDescription;
  }

  public void setConsumableDescription(int consumableDescription) {
    this.consumableDescription = consumableDescription;
  }

  @Override
  public String toString() {
    return "Consumable{" +
        "consumableID = "+this.getItemID()+" "+
        "consumableItemLevel=" + consumableItemLevel +
        ", consumableDescription=" + consumableDescription +
        '}';
  }
}
