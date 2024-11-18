package org.pm3.dal;

import java.sql.*;
import org.pm3.model.Job;

public class JobDao {
    protected ConnectionManager connectionManager;
    private static JobDao instance = null;

    // Private constructor for Singleton pattern
    protected JobDao() {
        connectionManager = new ConnectionManager();
    }

    // Singleton instance getter
    public static JobDao getInstance() {
        if (instance == null) {
            instance = new JobDao();
        }
        return instance;
    }

    // Create a new Job and return the Job object with jobID set
    public Job create(Job job) throws SQLException {
        String insertJob = "INSERT INTO Job (jobName) VALUES (?)";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertJob, Statement.RETURN_GENERATED_KEYS);

            insertStmt.setString(1, job.getJobName());
            insertStmt.executeUpdate();

            // Retrieve the generated jobID
            resultKey = insertStmt.getGeneratedKeys();
            if (resultKey.next()) {
                job.setJobID(resultKey.getInt(1));  // Set auto-generated jobID
            } else {
                throw new SQLException("Creating job failed, no ID obtained.");
            }

            return job;
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

    // Retrieve a Job by jobID
    public Job getJobById(int jobID) throws SQLException {
        String selectJob = "SELECT * FROM Job WHERE jobID = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet resultSet = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectJob);
            selectStmt.setInt(1, jobID);

            resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                String jobName = resultSet.getString("jobName");
                return new Job(jobID, jobName);
            } else {
                return null;  // No job found with the given jobID
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

    public Job updateJobName(Job job, String newJobName) throws SQLException {
        String updateJobNameSQL = "UPDATE Job SET jobName = ? WHERE jobID = ?";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateJobNameSQL);

            // Set the new job name in the query
            updateStmt.setString(1, newJobName);
            updateStmt.setInt(2, job.getJobID());

            // Execute the update
            updateStmt.executeUpdate();

            // Update the job's name in the object and return the updated object
            job.setJobName(newJobName);
            return job;
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


    // Delete a job from the database
    public void delete(Job job) throws SQLException {
        String deleteJob = "DELETE FROM Job WHERE jobID = ?";
        Connection connection = null;
        PreparedStatement deleteStmt = null;

        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteJob);
            deleteStmt.setInt(1, job.getJobID());
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
}
