package pm3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pm3.model.Gear;
import pm3.model.Item;
import pm3.model.Weapon;

public class GearDao extends ItemDao{
  protected ConnectionManager connectionManager;
  private static GearDao instance = null;

  // Private constructor for Singleton pattern
  protected GearDao() {
    connectionManager = new ConnectionManager();
  }

  // Singleton instance getter
  public static GearDao getInstance() {
    if (instance == null) {
      instance = new GearDao();
    }
    return instance;
  }

  public Gear create(Gear gear)  throws SQLException {
    Item item = create((Item) gear);
    String insertItem = "INSERT INTO Gear(gearID,gearItemLevel,gearRequiredLevel,gearDefenseRating,gearMagicDefenseRating) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertItem);
      insertStmt.setInt(1,item.getItemID());
      insertStmt.setInt(2,gear.getGearItemLevel());
      insertStmt.setInt(3,gear.getGearRequiredLevel());
      insertStmt.setDouble(4,gear.getGearDefenseRating());
      insertStmt.setDouble(5,gear.getGearMagicDefenseRating());
      insertStmt.executeUpdate();
      gear.setItemID(item.getItemID());
      return gear;
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
  public Gear getGearById(int gearId) throws SQLException {
    String selectItem = "SELECT gearID,gearItemLevel,gearRequiredLevel,gearDefenseRating,gearMagicDefenseRating " +
        "FROM Gear " +
        "WHERE gearID=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectItem);
      selectStmt.setInt(1,gearId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Item item = ItemDao.getInstance().getItembyId(gearId);
//        int gearId = results.getInt("weaponID");
        int itemLevel = results.getInt("gearItemLevel");
        int requiredLevel = results.getInt("gearRequiredLevel");
        int defenseRating = results.getInt("gearDefenseRating");
        int magicDefenseRating = results.getInt("gearMagicDefenseRating");
        Gear gear = new Gear(gearId,item.getItemName(),item.getItemMaxStackSize(),item.getSlotType(),item.getItemVendorPrice(),item.isAbleToSell(),item.getItemCategory(),itemLevel,requiredLevel,defenseRating,magicDefenseRating);
        return gear;

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
}
