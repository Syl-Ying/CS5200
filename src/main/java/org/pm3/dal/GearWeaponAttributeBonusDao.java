package org.pm3.dal;

import org.pm3.model.*;
import java.sql.*;


/**
 *  DAO class for GearWeaponAttributeBonus
 */
public class GearWeaponAttributeBonusDao {
	private static GearWeaponAttributeBonusDao instance = null;
    private ConnectionManager connectionManager;

    protected GearWeaponAttributeBonusDao() {
        connectionManager = new ConnectionManager();
    }

    public static GearWeaponAttributeBonusDao getInstance() {
        if (instance == null) {
            instance = new GearWeaponAttributeBonusDao();
        }
        return instance;
    }
    
    
    /**
     * 	Create a GearWeaponAttributeBonus table
     */
    public GearWeaponAttributeBonus create(GearWeaponAttributeBonus bonus) throws SQLException {
        String insertQuery = "INSERT INTO GearWeaponAttributeBonus(itemID, attributeID, bonuesValue) "+ 
        					 "VALUES (?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setInt(1, bonus.getItemID());
            insertStmt.setInt(2, bonus.getAttributeID());
            insertStmt.setInt(3, bonus.getBonusValue());
            insertStmt.executeUpdate();
            return bonus;
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
        }
    }
    
    
    /**
     * 	Get GearWeaponAttributeBonus by itemID and attributeID (compound key)
     */
    public GearWeaponAttributeBonus getGWAByItemIdAndAttributeID(int itemID, int attributeID) throws SQLException {
        String selectQuery = "SELECT itemID, attributeID, bonusValue " + 
        					 "FROM GearWeaponAttributeBonus " + 
        					 "WHERE itemID=? AND attributeID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setInt(1, itemID);
            selectStmt.setInt(2, attributeID);
            results = selectStmt.executeQuery();

            if (results.next()) {
                int resultItemID = results.getInt("itemID");
                int resultAttributeID = results.getInt("attributeID");
                int bonusValue = results.getInt("bonusValue");

                return new GearWeaponAttributeBonus(resultItemID, resultAttributeID, bonusValue);
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
    
    
    /**
     * Update the bonusValue of a GearWeaponAttributeBonus
     */
    public GearWeaponAttributeBonus update(
    			GearWeaponAttributeBonus gwaBonus, 
    			int newBonusValue) throws SQLException {
        String updateQuery = "UPDATE GearWeaponAttributeBonus" + 
        					 "SET bonusValue=?" + 
        		             "WHERE itemID=? AND attributeID=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setInt(1, newBonusValue);
            updateStmt.setInt(2, gwaBonus.getItemID());
            updateStmt.setInt(3, gwaBonus.getAttributeID());
            updateStmt.executeUpdate();

            // update the bonus object with the new value
            gwaBonus.setBonusValue(newBonusValue);

            return gwaBonus;
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

}
