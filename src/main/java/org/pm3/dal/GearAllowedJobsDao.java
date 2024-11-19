package org.pm3.dal;

import org.pm3.model.Gear;
import org.pm3.model.GearAllowedJobs;
import org.pm3.model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GearAllowedJobsDao {

    protected ConnectionManager connectionManager;
    private static GearAllowedJobsDao instance = null;

    protected GearAllowedJobsDao() {
        connectionManager = new ConnectionManager();
    }

    public static GearAllowedJobsDao getInstance() {
        if (instance == null) {
            instance = new GearAllowedJobsDao();
        }
        return instance;
    }

    public GearAllowedJobs create(GearAllowedJobs gearAllowedJobs) throws SQLException {
        String insertGearAllowedJobs = "INSERT INTO GearAllowedJobs(gearID,jobID,isAvaibility) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertGearAllowedJobs);
            insertStmt.setInt(1, gearAllowedJobs.getGear().getItemID());
            insertStmt.setInt(2, gearAllowedJobs.getJob().getJobID());
            insertStmt.setBoolean(3, gearAllowedJobs.isAvaibility());
            insertStmt.executeUpdate();
            return gearAllowedJobs;
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

    public GearAllowedJobs getGearAllowedJobsByGearIDAndJobID(int gearID, int jobID) throws SQLException {
        String selectGearAllowedJobs = "SELECT isAvaibility FROM GearAllowedJobs WHERE gearID=? AND jobID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGearAllowedJobs);
            selectStmt.setInt(1, gearID);
            selectStmt.setInt(2, jobID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                Boolean isAvailability = results.getBoolean("isAvaibility");
                Gear gear = new GearDao().getGearById(gearID);
                Job job = new JobDao().getJobById(jobID);
                return new GearAllowedJobs(gear, job, isAvailability);
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

    public GearAllowedJobs delete(GearAllowedJobs gearAllowedJobs) throws SQLException {
        String deleteGearAllowedJobs = "DELETE FROM GearAllowedJobs WHERE gearID=? AND jobID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteGearAllowedJobs);
            deleteStmt.setInt(1, gearAllowedJobs.getGear().getItemID());
            deleteStmt.setInt(2, gearAllowedJobs.getJob().getJobID());
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
