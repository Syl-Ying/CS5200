package org.pm3.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.pm3.model.SlotType;

public class SlotTypeDao {
  protected ConnectionManager connectionManager;
  private static SlotTypeDao instance = null;

  // Private constructor for Singleton pattern
  protected SlotTypeDao() {
    connectionManager = new ConnectionManager();
  }

  // Singleton instance getter
  public static SlotTypeDao getInstance() {
    if (instance == null) {
      instance = new SlotTypeDao();
    }
    return instance;
  }

  // Get a SlotType by slotID
  public SlotType getByID(int slotID) throws SQLException {
    String selectSlotType = "SELECT * FROM SlotType WHERE slotID = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectSlotType);
      selectStmt.setInt(1, slotID);

      resultSet = selectStmt.executeQuery();
      if (resultSet.next()) {
        int retrievedSlotID = resultSet.getInt("slotID");
        String name = resultSet.getString("name");

        return new SlotType(retrievedSlotID, name);
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

  // Create a new SlotType
  public SlotType create(SlotType slotType) throws SQLException {
    String insertSlotType = "INSERT INTO SlotType (name) VALUES (?)";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertSlotType, Statement.RETURN_GENERATED_KEYS);

      insertStmt.setString(1, slotType.getName());
      insertStmt.executeUpdate();

      // Retrieve the generated slotID
      resultKey = insertStmt.getGeneratedKeys();
      if (resultKey.next()) {
        slotType.setSlotID(resultKey.getInt(1));
      }
      return slotType;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
      if (resultKey != null) {
        resultKey.close();
      }
    }
  }

  // Update an existing SlotType
  public SlotType update(SlotType slotType) throws SQLException {
    String updateSlotType = "UPDATE SlotType SET name = ? WHERE slotID = ?";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateSlotType);

      updateStmt.setString(1, slotType.getName());
      updateStmt.setInt(2, slotType.getSlotID());
      updateStmt.executeUpdate();

      return slotType;
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

  // Delete a SlotType by slotID
  public void delete(int slotID) throws SQLException {
    String deleteSlotType = "DELETE FROM SlotType WHERE slotID = ?";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteSlotType);

      deleteStmt.setInt(1, slotID);
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

  // Get all SlotTypes
  public List<SlotType> getAll() throws SQLException {
    String selectAllSlotTypes = "SELECT * FROM SlotType";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    List<SlotType> slotTypes = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAllSlotTypes);
      resultSet = selectStmt.executeQuery();

      while (resultSet.next()) {
        int slotID = resultSet.getInt("slotID");
        String name = resultSet.getString("name");

        SlotType slotType = new SlotType(slotID, name);
        slotTypes.add(slotType);
      }
      return slotTypes;
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
