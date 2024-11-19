package org.pm3.dal;

import java.sql.*;
import org.pm3.model.*;


/**
 * 	DAO class for CharacterAttributeValues
 */
public class CharacterAttributeValuesDao {
	private static CharacterAttributeValuesDao instance = null;
    private ConnectionManager connectionManager;

    protected CharacterAttributeValuesDao() {
        connectionManager = new ConnectionManager();
    }

    public static CharacterAttributeValuesDao getInstance() {
        if (instance == null) {
            instance = new CharacterAttributeValuesDao();
        }
        return instance;
    }
    
    
    /**
     * 	Create CharacterAttributeValues table
     */
    public CharacterAttributeValues create(CharacterAttributeValues values) throws SQLException {
        String insertValues = 
        		"INSERT INTO CharacterAttributeValues(characterID, attributeID, attributeValue)" + 
        		"VALUES (?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertValues, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, values.getCharacterID());
            insertStmt.setInt(2, values.getAttributeID());
            insertStmt.setInt(3, values.getAttributeValue());
            insertStmt.executeUpdate();

            return values;
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
        
        
    /**
     * 	Get CharacterAttributeValues by characterID and attributeID(compound key)
     */
    public CharacterAttributeValues getByCharAttrById(int characterID, int attributeID) throws SQLException {
        String selectQuery = "SELECT characterID, attributeID, attributeValue " +
                                "FROM CharacterAttributeValues " + 
                                "WHERE characterID=? AND attributeID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setInt(1, characterID);
            selectStmt.setInt(2, attributeID);
            results = selectStmt.executeQuery();

            if (results.next()) {
                int resultCharacterID = results.getInt("characterID");
                int resultAttributeID = results.getInt("attributeID");
                int attributeValue = results.getInt("attributeValue");
                
                CharacterAttributeValues values = new CharacterAttributeValues(
                                    resultCharacterID, resultAttributeID, attributeValue);
                return values;
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
     * 	Delete CharacterAttributeValues By characterID AND attributeID
     */
    public CharacterAttributeValues delete(CharacterAttributeValues values) throws SQLException {
        String deleteQuery = "DELETE FROM CharacterAttributeValues " + 
                                "WHERE characterID=? AND attributeID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, values.getCharacterID());
            deleteStmt.setInt(2, values.getAttributeID());
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


