package pm3.model;

public class Weapon extends Item {
  int weaponItemLevel;
  int weaponRequiredLevel;
  double weaponDamageDone;
  double weaponAutoAttack;

  double weaponAttackDelay;

  public Weapon(int itemID, String itemName, int itemMaxStackSize, SlotType slotType,
      int itemVendorPrice, boolean isAbleToSell, String itemCategory, int weaponItemLevel,
      int weaponRequiredLevel, double weaponDamageDone, double weaponAutoAttack,
      double weaponAttackDelay) {
    super(itemID, itemName, itemMaxStackSize, slotType, itemVendorPrice, isAbleToSell,
        itemCategory);
    this.weaponItemLevel = weaponItemLevel;
    this.weaponRequiredLevel = weaponRequiredLevel;
    this.weaponDamageDone = weaponDamageDone;
    this.weaponAutoAttack = weaponAutoAttack;
    this.weaponAttackDelay = weaponAttackDelay;
  }

  public Weapon(String itemName, int itemMaxStackSize, SlotType slotType, int itemVendorPrice,
      boolean isAbleToSell, String itemCategory, int weaponItemLevel, int weaponRequiredLevel,
      double weaponDamageDone, double weaponAutoAttack, double weaponAttackDelay) {
    super(itemName, itemMaxStackSize, slotType, itemVendorPrice, isAbleToSell, itemCategory);
    this.weaponItemLevel = weaponItemLevel;
    this.weaponRequiredLevel = weaponRequiredLevel;
    this.weaponDamageDone = weaponDamageDone;
    this.weaponAutoAttack = weaponAutoAttack;
    this.weaponAttackDelay = weaponAttackDelay;
  }

  public int getWeaponItemLevel() {
    return weaponItemLevel;
  }

  public void setWeaponItemLevel(int weaponItemLevel) {
    this.weaponItemLevel = weaponItemLevel;
  }

  public int getWeaponRequiredLevel() {
    return weaponRequiredLevel;
  }

  public void setWeaponRequiredLevel(int weaponRequiredLevel) {
    this.weaponRequiredLevel = weaponRequiredLevel;
  }

  public double getWeaponDamageDone() {
    return weaponDamageDone;
  }

  public void setWeaponDamageDone(double weaponDamageDone) {
    this.weaponDamageDone = weaponDamageDone;
  }

  public double getWeaponAutoAttack() {
    return weaponAutoAttack;
  }

  public void setWeaponAutoAttack(double weaponAutoAttack) {
    this.weaponAutoAttack = weaponAutoAttack;
  }

  public double getWeaponAttackDelay() {
    return weaponAttackDelay;
  }

  public void setWeaponAttackDelay(double weaponAttackDelay) {
    this.weaponAttackDelay = weaponAttackDelay;
  }
//  public int getID(){
//    this.getItemID();
//  }

  @Override
  public String toString() {
    return "Weapon{"  +
        "WeaponID is=" + + this.getItemID() +" "+
        "weaponItemLevel=" + weaponItemLevel +
        ", weaponRequiredLevel=" + weaponRequiredLevel +
        ", weaponDamageDone=" + weaponDamageDone +
        ", weaponAutoAttack=" + weaponAutoAttack +
        ", weaponAttackDelay=" + weaponAttackDelay +
        '}';
  }
}
