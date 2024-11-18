package org.pm3.dal;

import org.pm3.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterJobDao {
    protected ConnectionManager connectionManager;
    private static CharacterJobDao instance = null;

    // Private constructor for Singleton pattern
    protected CharacterJobDao() {
    	 connectionManager = new ConnectionManager();
    }

    // Singleton instance getter
    public static CharacterJobDao getInstance() {
        if (instance == null) {
            instance = new CharacterJobDao();
        }
        return instance;
    }


 // Create a new CharacterJob and return the CharacterJob object
    public CharacterJob create(CharacterJob characterJob) throws SQLException {
        String insertCharacterJob = "INSERT INTO CharacterJob (characterID, jobID, characterJobLevel, characterJobExperiencePoints, characterJobThreshold) VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCharacterJob);

            insertStmt.setInt(1, characterJob.getCharacter().getCharacterID());
            insertStmt.setInt(2, characterJob.getJob().getJobID());
            insertStmt.setInt(3, characterJob.getCharacterJobLevel());
            insertStmt.setInt(4, characterJob.getCharacterJobExperiencePoints());
            insertStmt.setInt(5, characterJob.getCharacterJobThreshold());
            insertStmt.executeUpdate();

            // Return the new CharacterJob object
            return characterJob;
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


    // Retrieve a CharacterJob by characterID and jobID
    public CharacterJob getCharacterJob(int characterID, int jobID) throws SQLException {
        String getCharacterJob = "SELECT * FROM CharacterJob WHERE characterID = ? AND jobID = ?";
        Connection connection = null;
        PreparedStatement getStmt = null;
        ResultSet resultSet = null;

        try {
            connection = connectionManager.getConnection();
            getStmt = connection.prepareStatement(getCharacterJob);
            getStmt.setInt(1, characterID);
            getStmt.setInt(2, jobID);

            resultSet = getStmt.executeQuery();

            if (resultSet.next()) {
                // Create and return the CharacterJob object
                Characters character = new Characters(resultSet.getInt("characterID"));
                Job job = new Job(resultSet.getInt("jobID"));
                int characterJobLevel = resultSet.getInt("characterJobLevel");
                int characterJobExperiencePoints = resultSet.getInt("characterJobExperiencePoints");
                int characterJobThreshold = resultSet.getInt("characterJobThreshold");

                return new CharacterJob(character, job, characterJobLevel, characterJobExperiencePoints, characterJobThreshold);
            } else {
                return null; 
            }
        } finally {
            if (connection != null) connection.close();
            if (getStmt != null) getStmt.close();
            if (resultSet != null) resultSet.close();
        }
    }

    // Delete a CharacterJob entry
    public void delete(int characterID, int jobID) throws SQLException {
        String deleteCharacterJob = "DELETE FROM CharacterJob WHERE characterID = ? AND jobID = ?";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCharacterJob);
            deleteStmt.setInt(1, characterID);
            deleteStmt.setInt(2, jobID);

            deleteStmt.executeUpdate();

          
        } finally {
            if (connection != null) connection.close();
            if (deleteStmt != null) deleteStmt.close();
        }
    }

    public List<CharacterJob> getCharacterJobsByCharacterID(int characterID) throws SQLException {
        String getAllJobsForCharacter = 
            "SELECT cj.characterID, cj.jobID, cj.characterJobLevel, cj.characterJobExperiencePoints, cj.characterJobThreshold, j.jobName " +
            "FROM CharacterJob cj " +
            "JOIN Job j ON cj.jobID = j.jobID " +
            "WHERE cj.characterID = ?";
        
        Connection connection = null;
        PreparedStatement getStmt = null;
        ResultSet resultSet = null;
        List<CharacterJob> characterJobs = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            getStmt = connection.prepareStatement(getAllJobsForCharacter);
            getStmt.setInt(1, characterID);

            resultSet = getStmt.executeQuery();

            while (resultSet.next()) {
                // Create the CharacterJob object
                Characters character = new Characters(resultSet.getInt("characterID"));
                Job job = new Job(resultSet.getInt("jobID"), resultSet.getString("jobName")); // Fetch the job name here
                int characterJobLevel = resultSet.getInt("characterJobLevel");
                int characterJobExperiencePoints = resultSet.getInt("characterJobExperiencePoints");
                int characterJobThreshold = resultSet.getInt("characterJobThreshold");

                characterJobs.add(new CharacterJob(character, job, characterJobLevel, characterJobExperiencePoints, characterJobThreshold));
            }

            return characterJobs;
        } finally {
            if (connection != null) connection.close();
            if (getStmt != null) getStmt.close();
            if (resultSet != null) resultSet.close();
        }
    }

  
}
