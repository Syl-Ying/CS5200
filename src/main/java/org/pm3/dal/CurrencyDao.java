package org.pm3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.pm3.model.Currency;

public class CurrencyDao {
  protected ConnectionManager connectionManager;
  private static CurrencyDao instance = null;

  // Private constructor for Singleton pattern
  protected CurrencyDao() {
    connectionManager = new ConnectionManager();
  }

  // Singleton instance getter
  public static CurrencyDao getInstance() {
    if (instance == null) {
      instance = new CurrencyDao();
    }
    return instance;
  }

  // Get a Currency by currencyID
  public Currency getByID(int currencyID) throws SQLException {
    String selectCurrency = "SELECT * FROM Currency WHERE currencyID = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCurrency);
      selectStmt.setInt(1, currencyID);

      resultSet = selectStmt.executeQuery();
      if (resultSet.next()) {
        int retrievedCurrencyID = resultSet.getInt("currencyID");
        String currencyName = resultSet.getString("currencyName");
        double currencyMaxAmount = resultSet.getDouble("currencyMaxAmount");
        double currencyWeeklyCap = resultSet.getDouble("currencyWeeklyCap");

        return new Currency(retrievedCurrencyID, currencyName, currencyMaxAmount, currencyWeeklyCap);
      } else {
        return null;
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

  // Create a new Currency
  public Currency create(Currency currency) throws SQLException {
    String insertCurrency = "INSERT INTO Currency (currencyName, currencyMaxAmount, currencyWeeklyCap) VALUES (?, ?, ?)";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCurrency, Statement.RETURN_GENERATED_KEYS);

      insertStmt.setString(1, currency.getCurrencyName());
      insertStmt.setDouble(2, currency.getCurrencyMaxAmount());
      insertStmt.setDouble(3, currency.getCurrencyWeeklyCap());
      insertStmt.executeUpdate();

      // Retrieve the generated currencyID
      resultKey = insertStmt.getGeneratedKeys();
      if (resultKey.next()) {
        currency.setCurrencyID(resultKey.getInt(1));
      }
      return currency;
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

  // Update an existing Currency
  public Currency update(Currency currency) throws SQLException {
    String updateCurrency = "UPDATE Currency SET currencyName = ?, currencyMaxAmount = ?, currencyWeeklyCap = ? WHERE currencyID = ?";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateCurrency);

      updateStmt.setString(1, currency.getCurrencyName());
      updateStmt.setDouble(2, currency.getCurrencyMaxAmount());
      updateStmt.setDouble(3, currency.getCurrencyWeeklyCap());
      updateStmt.setInt(4, currency.getCurrencyID());

      updateStmt.executeUpdate();
      return currency;
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

  // Delete a Currency by currencyID
  public void delete(int currencyID) throws SQLException {
    String deleteCurrency = "DELETE FROM Currency WHERE currencyID = ?";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCurrency);
      deleteStmt.setInt(1, currencyID);
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

  // Get all Currencies
  public List<Currency> getAll() throws SQLException {
    String selectAllCurrencies = "SELECT * FROM Currency";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    List<Currency> currencies = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectAllCurrencies);
      resultSet = selectStmt.executeQuery();

      while (resultSet.next()) {
        int currencyID = resultSet.getInt("currencyID");
        String currencyName = resultSet.getString("currencyName");
        double currencyMaxAmount = resultSet.getDouble("currencyMaxAmount");
        double currencyWeeklyCap = resultSet.getDouble("currencyWeeklyCap");

        Currency currency = new Currency(currencyID, currencyName, currencyMaxAmount, currencyWeeklyCap);
        currencies.add(currency);
      }
      return currencies;
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
}
