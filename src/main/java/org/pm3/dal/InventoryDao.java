package org.pm3.dal;

import org.pm3.model.Characters;
import org.pm3.model.Inventory;
import org.pm3.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao {

    protected ConnectionManager connectionManager;

    private static InventoryDao instance = null;

    protected InventoryDao() {
        connectionManager = new ConnectionManager();
    }

    public static InventoryDao getInstance() {
        if (instance == null) {
            instance = new InventoryDao();
        }
        return instance;
    }

    public Inventory create(Inventory inventory) throws SQLException {
        String insertInventory =
                "INSERT INTO Inventory (itemID, characterID, inventorySlotNumber, currentItemQuantity) " +
                        "VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertInventory);
            insertStmt.setInt(1, inventory.getItem().getItemID());
            insertStmt.setInt(2, inventory.getCharacter().getCharacterID());
            insertStmt.setInt(3, inventory.getInventorySlotNumber());
            insertStmt.setInt(4, inventory.getCurrentItemQuantity());
            insertStmt.executeUpdate();
            return inventory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }

    public Inventory getInventoryByCharacterAndItem(Characters character, Item item) throws SQLException {
        String selectInventory = "SELECT inventorySlotNumber,currentItemQuantity FROM Inventory " +
                "WHERE characterID=? and itemID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInventory);
            selectStmt.setInt(1, character.getCharacterID());
            selectStmt.setInt(2, item.getItemID());
            results = selectStmt.executeQuery();
            if (results.next()) {
                int inventorySlotNumber = results.getInt("inventorySlotNumber");
                int currentItemQuantity = results.getInt("currentItemQuantity");
                Inventory inventory = new Inventory(character, item, currentItemQuantity, inventorySlotNumber);
                return inventory;
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
            if (results != null) {
                results.close();
            }
        }

        return null;
    }

    public List<Inventory> getInventoryByCharacter(Characters character) throws SQLException {
        List<Inventory> inventories = new ArrayList<>();
        String selectInventory =
                "SELECT itemID,inventorySlotNumber,currentItemQuantity " +
                        "FROM Inventory " +
                        "WHERE characterID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectInventory);
            selectStmt.setInt(1, character.getCharacterID());
            results = selectStmt.executeQuery();
            while (results.next()) {
                int itemID = results.getInt("itemID");
                Item item = ItemDao.getInstance().getItembyId(itemID);
                int inventorySlotNumber = results.getInt("inventorySlotNumber");
                int currentItemQuantity = results.getInt("currentItemQuantity");

                Inventory inventory = new Inventory(character, item, currentItemQuantity, inventorySlotNumber);
                inventories.add(inventory);
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
            if (results != null) {
                results.close();
            }
        }

        return inventories;
    }

    public Inventory updateInventory(Inventory inventory, int newQuantity) throws SQLException {
        String updateInventory = "UPDATE Inventory SET currentItemQuantity=? WHERE characterID=? and itemID=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateInventory);
            updateStmt.setInt(1, newQuantity);
            updateStmt.setInt(2, inventory.getCharacter().getCharacterID());
            updateStmt.setInt(3, inventory.getItem().getItemID());
            updateStmt.executeUpdate();
            inventory.setCurrentItemQuantity(newQuantity);
            return inventory;
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

    public Inventory delete(Inventory inventory) throws SQLException {
        String deleteInventory = "DELETE FROM Inventory WHERE characterID=? and itemID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteInventory);
            deleteStmt.setInt(1, inventory.getCharacter().getCharacterID());
            deleteStmt.setInt(2, inventory.getItem().getItemID());
            deleteStmt.executeUpdate();
            return null;
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
}
