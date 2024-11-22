package pm3.dal;

import java.sql.*;
import pm3.model.*;


/**
 * 	DAO class for CharacterAttribute
 */
public class CharacterAttributeDao {
	private static CharacterAttributeDao instance = null;
    private ConnectionManager connectionManager;

    protected CharacterAttributeDao() {
        connectionManager = new ConnectionManager();; 
    }

    public static CharacterAttributeDao getInstance() {
        if (instance == null) {
            instance = new CharacterAttributeDao();
        }
        return instance;
    }
    
    
    /**
     * 	Create a CharacterAttributeValue table
     */
    public CharacterAttribute create(CharacterAttribute attribute) throws SQLException {
        String insertAttribute = "INSERT INTO CharacterAttribute(attributeName) VALUES (?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertAttribute, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, attribute.getAttributeName());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            if (resultKey.next()) {
                int attributeID = resultKey.getInt(1);
                attribute.setAttributeID(attributeID);
            }
            return attribute;
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
     * 	Get CharacterAttribute By attributeID
     */
    public CharacterAttribute getByAttributeId(int attributeID) throws SQLException {
        String selectQuery = "SELECT attributeID, attributeName " + 
        					 "FROM CharacterAttribute " + 
        					 "WHERE attributeID = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setInt(1, attributeID);
            results = selectStmt.executeQuery();
            
            if (results.next()) {
            	int resultAttributeID = results.getInt("attributeID");
                String attributeName = results.getString("attributeName");
                CharacterAttribute characterAttribute = new CharacterAttribute(
                												resultAttributeID,attributeName);
                return characterAttribute;
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
     * 	Delete CharacterAttribute By attributeID
     */
    public CharacterAttribute delete(CharacterAttribute attribute) throws SQLException {
        String deleteCharacterAttribute = "DELETE FROM CharacterAttribute WHERE attributeID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCharacterAttribute);
            deleteStmt.setInt(1, attribute.getAttributeID());
            
            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for attributeID=" + attribute.getAttributeID());
            }
            
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
