package pm3.dal;

import java.sql.*;

import pm3.model.Player;

public class PlayerDao {
    protected ConnectionManager connectionManager;
    private static PlayerDao instance = null;

    // Private constructor for Singleton pattern
    protected PlayerDao() {
        connectionManager = new ConnectionManager();
    }

    // Singleton instance getter
    public static PlayerDao getInstance() {
        if (instance == null) {
            instance = new PlayerDao();
        }
        return instance;
    }

    // Create a new player and return the player object with playerID set
    public Player create(Player player) throws SQLException {
        String insertPlayer = "INSERT INTO Player (playerName, playerEmail) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPlayer, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1, player.getPlayerName());
            insertStmt.setString(2, player.getPlayerEmail());
            
            insertStmt.executeUpdate();
            
            // Retrieve the generated playerID
            resultKey = insertStmt.getGeneratedKeys();
            if (resultKey.next()) {
                player.setPlayerID(resultKey.getInt(1));  // Set auto-generated playerID
            } else {
                throw new SQLException("Creating player failed, no ID obtained.");
            }

            return player;
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

    // Read a player by playerID
    public Player getPlayerById(int playerID) throws SQLException {
        String query = "SELECT * FROM Player WHERE playerID = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionManager.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, playerID);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return new Player(
                        resultSet.getInt("playerID"),
                        resultSet.getString("playerName"),
                        resultSet.getString("playerEmail")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return null; // Player not found
    }


 // Delete a player
    public Player delete(Player player) throws SQLException {
        String deletePlayer = "DELETE FROM Player WHERE playerID = ?";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePlayer);
            deleteStmt.setInt(1, player.getPlayerID());
            deleteStmt.executeUpdate();

            return null; // Returning null after deletion
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        } finally {
            // Clean up resources
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

}



