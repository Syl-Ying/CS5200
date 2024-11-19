package org.pm3.dal;
import org.pm3.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.pm3.model.Item;
import org.pm3.model.SlotType;
import org.pm3.model.Weapon;

public class WeaponDao extends ItemDao{
  protected ConnectionManager connectionManager;
  private static WeaponDao instance = null;

  // Private constructor for Singleton pattern
  protected WeaponDao() {
    connectionManager = new ConnectionManager();
  }

  // Singleton instance getter
  public static WeaponDao getInstance() {
    if (instance == null) {
      instance = new WeaponDao();
    }
    return instance;
  }
  public Weapon getItembyId(int weaponId) throws SQLException {
    String selectItem = "SELECT weaponID,weaponItemLevel,weaponRequiredLevel,weaponDamageDone,weaponAutoAttack,weaponAttackDelay " +
        "FROM Weapon " +
        "WHERE weaponID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectItem);
      selectStmt.setInt(1,weaponId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Item item = ItemDao.getInstance().getItembyId(weaponId);
        int weaponID = results.getInt("weaponID");
        int weaponItemLevel = results.getInt("weaponItemLevel");
        int weaponRequiredLevel = results.getInt("weaponRequiredLevel");
        int weaponDamageDone = results.getInt("weaponDamageDone");
        double weaponAutoAttack = results.getDouble("weaponAutoAttack");
        double weaponAttackDelay = results.getDouble("weaponAttackDelay");
        Weapon newWeapon = new Weapon(weaponID,item.getItemName(),item.getItemMaxStackSize(),item.getSlotType(),item.getItemVendorPrice(),item.isAbleToSell(),item.getItemCategory(),weaponItemLevel,weaponRequiredLevel,weaponDamageDone,weaponAutoAttack,weaponAttackDelay);
        return newWeapon;

//        return new Item(itemId,itemName,itemMaxStackSize,slotTypeTemp,itemVendorPrice,isAbleToSell,itemCategory);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;

  }


  public Weapon create(Weapon weapon)  throws SQLException {
    Item item = create((Item) weapon);
    String insertItem = "INSERT INTO Weapon(weaponID,weaponItemLevel,weaponRequiredLevel,weaponDamageDone,weaponAutoAttack,weaponAttackDelay) VALUES(?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertItem);
      insertStmt.setInt(1,item.getItemID());
      insertStmt.setInt(2,weapon.getWeaponItemLevel());
      insertStmt.setInt(3,weapon.getWeaponRequiredLevel());
      insertStmt.setDouble(4,weapon.getWeaponDamageDone());
      insertStmt.setDouble(5,weapon.getWeaponAutoAttack());
      insertStmt.setDouble(6,weapon.getWeaponAttackDelay());
      insertStmt.executeUpdate();
      weapon.setItemID(item.getItemID());
      return weapon;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
    }


  }




}
