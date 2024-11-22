package pm3.model;

public class EquippedItem {
  private Characters character;
  private SlotType slotType;
  private Item item;

  public EquippedItem() {
  }

  public EquippedItem(Characters character, SlotType slotType, Item item) {
    this.character = character;
    this.slotType = slotType;
    this.item = item;
  }

  public Characters getCharacter() {
    return character;
  }

  public void setCharacter(Characters character) {
    this.character = character;
  }

  public SlotType getSlotType() {
    return slotType;
  }

  public void setSlotType(SlotType slotType) {
    this.slotType = slotType;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

}
