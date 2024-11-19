package org.pm3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pm3.model.Consumable;
import org.pm3.model.Gear;
import org.pm3.model.Item;

public class ConsumableDao extends ItemDao{
  protected ConnectionManager connectionManager;
  private static ConsumableDao instance = null;

  // Private constructor for Singleton pattern
  protected ConsumableDao() {
    connectionManager = new ConnectionManager();
  }

  // Singleton instance getter
  public static ConsumableDao getInstance() {
    if (instance == null) {
      instance = new ConsumableDao();
    }
    return instance;
  }
  public Consumable create(Consumable consumable)  throws SQLException {
    Item item = create((Item) consumable);
    String insertItem = "INSERT INTO Consumable(consumableID,consumableItemLevel,consumableDescription) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertItem);
      insertStmt.setInt(1,item.getItemID());
      insertStmt.setInt(2,consumable.getConsumableItemLevel());
     insertStmt.setInt(3,consumable.getConsumableDescription());
      insertStmt.executeUpdate();
      consumable.setItemID(item.getItemID());
      return consumable;
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

  public Consumable getConsumableById(int consumableId) throws SQLException {
    String selectItem = "SELECT consumableID,consumableItemLevel,consumableDescription " +
        "FROM Consumable " +
        "WHERE consumableId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectItem);
      selectStmt.setInt(1,consumableId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Item item = ItemDao.getInstance().getItembyId(consumableId);
        int itemLevel = results.getInt("consumableItemLevel");
        int consumableDescription = results.getInt("consumableDescription");
        Consumable consumable = new Consumable(consumableId,item.getItemName(),item.getItemMaxStackSize(),item.getSlotType(),item.getItemVendorPrice(),item.isAbleToSell(),item.getItemCategory(),itemLevel,consumableDescription);
        return consumable;
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
