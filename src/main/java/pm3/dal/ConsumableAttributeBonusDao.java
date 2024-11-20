package pm3.dal;

import java.sql.*;
import pm3.model.*;


/**
 *  DAO Class for ConsumableAttributeBonus
 */
public class ConsumableAttributeBonusDao {
	private static ConsumableAttributeBonusDao instance = null;
    private ConnectionManager connectionManager;

    protected ConsumableAttributeBonusDao() {
        connectionManager = new ConnectionManager();
    }

    public static ConsumableAttributeBonusDao getInstance() {
        if (instance == null) {
            instance = new ConsumableAttributeBonusDao();
        }
        return instance;
    }
	
    
    /**
     * 	Create a ConsumableAttributeBonus table
     */
    public ConsumableAttributeBonus create(ConsumableAttributeBonus bonus) throws SQLException {
        String insertQuery = 
        "INSERT INTO ConsumableAttributeBonus(consumableID, attributeID, bonusPercentage, bonusCap) " + 
        "VALUES (?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setInt(1, bonus.getConsumableID());
            insertStmt.setInt(2, bonus.getAttributeID());
            insertStmt.setDouble(3, bonus.getBonusPercentage());
            insertStmt.setInt(4, bonus.getBonusCap());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            if (resultKey.next()) {
                bonus.setBonusID(resultKey.getInt(1));
            }
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
            if (resultKey != null) {
            	resultKey.close();
            }
        }
    }
    
    
    /**
     * 	Get ConsumableAttributeBonus by bonusID
     */
    public ConsumableAttributeBonus getConsuAttriByBonusId(int bonusID) throws SQLException {
        String selectQuery = "SELECT bonusID, consumableID, attributeID, bonusPercentage, bonusCap " +
                             "FROM ConsumableAttributeBonus WHERE bonusID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setInt(1, bonusID);
            results = selectStmt.executeQuery();

            if (results.next()) {
                int resultBonusID = results.getInt("bonusID");
                int consumableID = results.getInt("consumableID");
                int attributeID = results.getInt("attributeID");
                double bonusPercentage = results.getDouble("bonusPercentage");
                int bonusCap = results.getInt("bonusCap");

                return new ConsumableAttributeBonus(
                		resultBonusID,consumableID, attributeID, 
                		bonusPercentage, bonusCap);
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
     * 	Update ConsumableAttributeBonus
     */
    public ConsumableAttributeBonus update(
    		ConsumableAttributeBonus bonus, double newBonusPercentage, int newBonusCap
    		) throws SQLException {
        String updateQuery = 
            "UPDATE ConsumableAttributeBonus SET bonusPercentage=?, bonusCap=? WHERE bonusID=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateQuery);
            updateStmt.setDouble(1, newBonusPercentage);
            updateStmt.setInt(2, newBonusCap);
            updateStmt.setInt(3, bonus.getBonusID());
            updateStmt.executeUpdate();

            bonus.setBonusPercentage(newBonusPercentage);
            bonus.setBonusCap(newBonusCap);

            return bonus;
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
