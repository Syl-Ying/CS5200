package org.pm3.dal;

import java.sql.*;

import org.pm3.model.*;


public class CharactersDao {
    protected ConnectionManager connectionManager;
    private static CharactersDao instance = null;

    // Private constructor for Singleton pattern
    protected CharactersDao() {
        connectionManager = new ConnectionManager();
    }

    // Singleton instance getter
    public static CharactersDao getInstance() {
        if (instance == null) {
            instance = new CharactersDao();
        }
        return instance;
    }

    // Create a new Character and return the character object with characterID set
    public Characters create(Characters character) throws SQLException {
        String insertCharacter = "INSERT INTO Characters (playerID, characterFirstName, characterLastName, currentMaxHP) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacter, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setInt(1, character.getPlayer().getPlayerID());  
            insertStmt.setString(2, character.getCharacterFirstName());
            insertStmt.setString(3, character.getCharacterLastName());
            insertStmt.setInt(4, character.getCurrentMaxHP());
            
            insertStmt.executeUpdate();
            
            // Retrieve the generated characterID
            resultKey = insertStmt.getGeneratedKeys();
            if (resultKey.next()) {
                character.setCharacterID(resultKey.getInt(1));  // Set auto-generated characterID
            } else {
                throw new SQLException("Creating character failed, no ID obtained.");
            }

            return character;
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

 // Get a character by characterID
    public Characters getCharacterByID(int characterID) throws SQLException {
        String selectCharacter = "SELECT * FROM Characters WHERE characterID = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet resultSet = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCharacter);
            selectStmt.setInt(1, characterID);  // Set the characterID in the query

            resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                // Create a new Character object from the result set
                int playerID = resultSet.getInt("playerID");
                String characterFirstName = resultSet.getString("characterFirstName");
                String characterLastName = resultSet.getString("characterLastName");
                int currentMaxHP = resultSet.getInt("currentMaxHP");

                // Create a Player object and set its playerID
                Player player = new Player();  // Assume you have a Player object created elsewhere
                player.setPlayerID(playerID);  // Set the playerID

                // Return a new Characters object with the retrieved data
                return new Characters(characterID, player, characterFirstName, characterLastName, currentMaxHP);
            } else {
                return null;  // No character found with the given characterID
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
    
 // Update character details
    public Characters update(Characters character) throws SQLException {
        String updateCharacter = "UPDATE Characters SET characterFirstName = ?, characterLastName = ?, currentMaxHP = ? WHERE characterID = ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        
        try {
            // Get connection from the connection manager
            connection = connectionManager.getConnection();
            
            // Prepare the update statement
            updateStmt = connection.prepareStatement(updateCharacter);
            
            // Set the parameters: character's new first name, last name, and max HP
            updateStmt.setString(1, character.getCharacterFirstName());
            updateStmt.setString(2, character.getCharacterLastName());
            updateStmt.setInt(3, character.getCurrentMaxHP());
            updateStmt.setInt(4, character.getCharacterID());  // Set the characterID for identifying the record
            
            // Execute the update
            updateStmt.executeUpdate();
            
            return character;
        } catch (SQLException e) {
            
            e.printStackTrace();
            throw e;
        } finally {
            // Close resources
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }


}

