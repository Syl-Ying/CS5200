package org.pm3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.pm3.model.Item;
import org.pm3.model.SlotType;

public class ItemDao {
  protected ConnectionManager connectionManager;
  private static ItemDao instance = null;

  // Private constructor for Singleton pattern
  protected ItemDao() {
    connectionManager = new ConnectionManager();
  }

  // Singleton instance getter
  public static ItemDao getInstance() {
    if (instance == null) {
      instance = new ItemDao();
    }
    return instance;
  }
  public Item getItembyId(int itemId) throws SQLException {
    String selectItem = "SELECT itemId,itemName,allowedEquippedSlotID,itemMaxStackSize,itemVendorPrice,isAbleToSell,itemCategory " +
        "FROM Item " +
        "WHERE itemId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectItem);
      selectStmt.setInt(1,itemId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String itemName = results.getString("itemName");

        int itemMaxStackSize = results.getInt("itemMaxStackSize");
        int slotTypeId = results.getInt("allowedEquippedSlotID");
        SlotType slotTypeTemp = SlotTypeDao.getInstance().getByID(slotTypeId);
        int itemVendorPrice = results.getInt("itemVendorPrice");
        boolean isAbleToSell = results.getBoolean("isAbleToSell");
        String itemCategory = results.getString("itemCategory");
        return new Item(itemId,itemName,itemMaxStackSize,slotTypeTemp,itemVendorPrice,isAbleToSell,itemCategory);
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



  public Item create(Item item)  throws SQLException {
    String insertItem = "INSERT INTO Item(itemName,allowedEquippedSlotID,itemMaxStackSize,itemVendorPrice,isAbleToSell,itemCategory) VALUES(?,?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1,item.getItemName());
      insertStmt.setInt(2,item.getSlotType().getSlotID());
      insertStmt.setInt(3,item.getItemMaxStackSize());
      insertStmt.setInt(4,item.getItemVendorPrice());
      insertStmt.setBoolean(5,item.isAbleToSell());
      insertStmt.setString(6,item.getItemCategory());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      int itemId = 0;
      if(resultKey.next()){
        itemId=resultKey.getInt(1);
      }
      item.setItemID(itemId);
      return item;
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
