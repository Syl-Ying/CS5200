package org.pm3.dal;

import org.pm3.model.EquippedItem;
import org.pm3.model.Characters;
import org.pm3.model.SlotType;
import org.pm3.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquippedItemDao {
  private static EquippedItemDao instance = null;
  private ConnectionManager connectionManager;

  private EquippedItemDao() {
    connectionManager = new ConnectionManager();
  }

  public static EquippedItemDao getInstance() {
    if (instance == null) {
      instance = new EquippedItemDao();
    }
    return instance;
  }

  public EquippedItem create(EquippedItem equippedItem) throws SQLException {
    String insertEquippedItem = "INSERT INTO EquippedItem (characterID, slotID, itemID) VALUES (?, ?, ?)";

    try (Connection connection = connectionManager.getConnection();
        PreparedStatement insertStmt = connection.prepareStatement(insertEquippedItem)) {
      insertStmt.setInt(1, equippedItem.getCharacter().getCharacterID());
      insertStmt.setInt(2, equippedItem.getSlotType().getSlotID());
      insertStmt.setInt(3, equippedItem.getItem().getItemID());
      insertStmt.executeUpdate();

      return equippedItem;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void delete(EquippedItem equippedItem) throws SQLException {
    String deleteEquippedItem = "DELETE FROM EquippedItem WHERE characterID = ? AND slotID = ?";

    try (Connection connection = connectionManager.getConnection();
        PreparedStatement deleteStmt = connection.prepareStatement(deleteEquippedItem)) {
      deleteStmt.setInt(1, equippedItem.getCharacter().getCharacterID());
      deleteStmt.setInt(2, equippedItem.getSlotType().getSlotID());
      deleteStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    }
  }


  // Get EquippedItem by characterID and slotID
  public EquippedItem getEquippedItemByCharacterIDAndSlotID(int characterID, int slotID) throws SQLException {
    String selectEquippedItem = "SELECT * FROM EquippedItem WHERE characterID = ? AND slotID = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectEquippedItem);
      selectStmt.setInt(1, characterID);
      selectStmt.setInt(2, slotID);

      resultSet = selectStmt.executeQuery();
      if (resultSet.next()) {
        Characters character = CharactersDao.getInstance().getCharacterByID(characterID);
        SlotType slotType = SlotTypeDao.getInstance().getByID(slotID);
        Item item = ItemDao.getInstance().getItembyId(resultSet.getInt("itemID"));

        return new EquippedItem(character, slotType, item);
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (resultSet != null) {
        resultSet.close();
      }
    }
  }

  // Update an existing EquippedItem
  public EquippedItem updateItem(EquippedItem equippedItem) throws SQLException {
    String updateEquippedItem = "UPDATE EquippedItem SET itemID = ? WHERE characterID = ? AND slotID = ?";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateEquippedItem);

      updateStmt.setInt(1, equippedItem.getItem().getItemID());
      updateStmt.setInt(2, equippedItem.getCharacter().getCharacterID());
      updateStmt.setInt(3, equippedItem.getSlotType().getSlotID());

      updateStmt.executeUpdate();
      return equippedItem;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  // Delete EquippedItem by characterID and slotID
  public void delete(int characterID, int slotID) throws SQLException {
    String deleteEquippedItem = "DELETE FROM EquippedItem WHERE characterID = ? AND slotID = ?";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteEquippedItem);

      deleteStmt.setInt(1, characterID);
      deleteStmt.setInt(2, slotID);
      deleteStmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  // Get all EquippedItems for a given characterID
  public List<EquippedItem> getByCharacterID(int characterID) throws SQLException {
    String selectEquippedItems = "SELECT * FROM EquippedItem WHERE characterID = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    List<EquippedItem> equippedItems = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectEquippedItems);
      selectStmt.setInt(1, characterID);

      resultSet = selectStmt.executeQuery();
      while (resultSet.next()) {
        SlotType slotType = SlotTypeDao.getInstance().getByID(resultSet.getInt("slotID"));
        Item item = ItemDao.getInstance().getItembyId(resultSet.getInt("itemID"));
        Characters character = CharactersDao.getInstance().getCharacterByID(characterID);

        EquippedItem equippedItem = new EquippedItem(character, slotType, item);
        equippedItems.add(equippedItem);
      }
      return equippedItems;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (resultSet != null) {
        resultSet.close();
      }
    }
  }
}
