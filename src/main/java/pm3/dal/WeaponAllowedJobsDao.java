package pm3.dal;

import pm3.model.Job;
import pm3.model.Weapon;
import pm3.model.WeaponAllowedJobs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeaponAllowedJobsDao {

    protected ConnectionManager connectionManager;
    private static WeaponAllowedJobsDao instance = null;

    protected WeaponAllowedJobsDao() {
        connectionManager = new ConnectionManager();
    }

    public static WeaponAllowedJobsDao getInstance() {
        if (instance == null) {
            instance = new WeaponAllowedJobsDao();
        }
        return instance;
    }

    public WeaponAllowedJobs create(WeaponAllowedJobs weaponAllowedJobs) throws SQLException {
        String insertWeaponAllowedJobs = "INSERT INTO WeaponAllowedJobs(weaponID,jobID,isAvailability) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertWeaponAllowedJobs);
            insertStmt.setInt(1, weaponAllowedJobs.getWeapon().getItemID());
            insertStmt.setInt(2, weaponAllowedJobs.getJob().getJobID());
            insertStmt.setBoolean(3, weaponAllowedJobs.isAvailability());
            insertStmt.executeUpdate();
            return weaponAllowedJobs;
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

    public WeaponAllowedJobs getWeaponAllowedJobsByWeaponIDAndJobID(int weaponID, int jobID) throws SQLException {
        String selectWeaponAllowedJobs = "SELECT isAvailability FROM WeaponAllowedJobs WHERE weaponID=? AND jobID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectWeaponAllowedJobs);
            selectStmt.setInt(1, weaponID);
            selectStmt.setInt(2, jobID);
            results = selectStmt.executeQuery();
            if (results.next()) {
                Boolean isAvailability = results.getBoolean("isAvailability");
                Weapon weapon = new WeaponDao().getItembyId(weaponID);
                Job job = new JobDao().getJobById(jobID);
                return new WeaponAllowedJobs(weapon, job, isAvailability);
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

    public WeaponAllowedJobs delete(WeaponAllowedJobs weaponAllowedJobs) throws SQLException {
        String deleteWeaponAllowedJobs = "DELETE FROM WeaponAllowedJobs WHERE weaponID=? AND jobID=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteWeaponAllowedJobs);
            deleteStmt.setInt(1, weaponAllowedJobs.getWeapon().getItemID());
            deleteStmt.setInt(2, weaponAllowedJobs.getJob().getJobID());
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
