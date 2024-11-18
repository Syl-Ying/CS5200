package org.pm3.model;

public class SlotType {
  private int slotID;
  private String name;

  public SlotType() {
  }

  public SlotType(int slotID, String name) {
    this.slotID = slotID;
    this.name = name;
  }

  public int getSlotID() {
    return slotID;
  }

  public void setSlotID(int slotID) {
    this.slotID = slotID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
