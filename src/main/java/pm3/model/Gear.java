package pm3.model;

public class Gear extends Item{
  private int gearItemLevel;
  private int gearRequiredLevel;
  private int gearDefenseRating;
  private int gearMagicDefenseRating;

  public Gear(int itemID, String itemName, int itemMaxStackSize, SlotType slotType,
      int itemVendorPrice, boolean isAbleToSell, String itemCategory, int gearItemLevel,
      int gearRequiredLevel, int gearDefenseRating, int gearMagicDefenseRating) {
    super(itemID, itemName, itemMaxStackSize, slotType, itemVendorPrice, isAbleToSell,
        itemCategory);
    this.gearItemLevel = gearItemLevel;
    this.gearRequiredLevel = gearRequiredLevel;
    this.gearDefenseRating = gearDefenseRating;
    this.gearMagicDefenseRating = gearMagicDefenseRating;
  }

  public Gear(String itemName, int itemMaxStackSize, SlotType slotType, int itemVendorPrice,
      boolean isAbleToSell, String itemCategory, int gearItemLevel, int gearRequiredLevel,
      int gearDefenseRating, int gearMagicDefenseRating) {
    super(itemName, itemMaxStackSize, slotType, itemVendorPrice, isAbleToSell, itemCategory);
    this.gearItemLevel = gearItemLevel;
    this.gearRequiredLevel = gearRequiredLevel;
    this.gearDefenseRating = gearDefenseRating;
    this.gearMagicDefenseRating = gearMagicDefenseRating;
  }

  public int getGearItemLevel() {
    return gearItemLevel;
  }

  public void setGearItemLevel(int gearItemLevel) {
    this.gearItemLevel = gearItemLevel;
  }

  public int getGearRequiredLevel() {
    return gearRequiredLevel;
  }

  public void setGearRequiredLevel(int gearRequiredLevel) {
    this.gearRequiredLevel = gearRequiredLevel;
  }

  public int getGearDefenseRating() {
    return gearDefenseRating;
  }

  public void setGearDefenseRating(int gearDefenseRating) {
    this.gearDefenseRating = gearDefenseRating;
  }

  public int getGearMagicDefenseRating() {
    return gearMagicDefenseRating;
  }

  public void setGearMagicDefenseRating(int gearMagicDefenseRating) {
    this.gearMagicDefenseRating = gearMagicDefenseRating;
  }

  @Override
  public String toString() {
    return "Gear{" +
        "gearId=" +this.getItemID()+" "+
        "gearItemLevel=" + gearItemLevel +
        ", gearRequiredLevel=" + gearRequiredLevel +
        ", gearDefenseRating=" + gearDefenseRating +
        ", gearMagicDefenseRating=" + gearMagicDefenseRating +
        '}';
  }
}
