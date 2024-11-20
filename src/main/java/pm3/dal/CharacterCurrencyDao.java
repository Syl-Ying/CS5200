package pm3.dal;

import pm3.model.CharacterCurrency;
import pm3.model.Characters;
import pm3.model.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterCurrencyDao {
  protected ConnectionManager connectionManager;
  private static CharacterCurrencyDao instance = null;

  // Private constructor for Singleton pattern
  protected CharacterCurrencyDao() {
    connectionManager = new ConnectionManager();
  }

  // Singleton instance getter
  public static CharacterCurrencyDao getInstance() {
    if (instance == null) {
      instance = new CharacterCurrencyDao();
    }
    return instance;
  }

  // Create a new CharacterCurrency
  public CharacterCurrency create(CharacterCurrency characterCurrency) throws SQLException {
    String insertCharacterCurrency = "INSERT INTO CharacterCurrency (characterID, currencyID, currencyTotalAmount, currencyEarnedThisWeek) VALUES (?, ?, ?, ?)";
    Connection connection = null;
    PreparedStatement insertStmt = null;

    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCharacterCurrency);

      insertStmt.setInt(1, characterCurrency.getCharacter().getCharacterID());
      insertStmt.setInt(2, characterCurrency.getCurrency().getCurrencyID());
      insertStmt.setDouble(3, characterCurrency.getCurrencyTotalAmount());
      insertStmt.setDouble(4, characterCurrency.getCurrencyEarnedThisWeek());

      insertStmt.executeUpdate();
      return characterCurrency;
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

  // Get CharacterCurrency by characterID and currencyID
  public CharacterCurrency getByCharacterIDAndCurrencyID(int characterID, int currencyID) throws SQLException {
    String selectCharacterCurrency = "SELECT * FROM CharacterCurrency WHERE characterID = ? AND currencyID = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCharacterCurrency);
      selectStmt.setInt(1, characterID);
      selectStmt.setInt(2, currencyID);

      resultSet = selectStmt.executeQuery();
      if (resultSet.next()) {
        Characters character = CharactersDao.getInstance().getCharacterByID(characterID);
        Currency currency = CurrencyDao.getInstance().getByID(currencyID);

        double totalAmount = resultSet.getDouble("currencyTotalAmount");
        double earnedThisWeek = resultSet.getDouble("currencyEarnedThisWeek");

        return new CharacterCurrency(character, currency, totalAmount, earnedThisWeek);
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

  // Update CharacterCurrency
  public CharacterCurrency update(CharacterCurrency characterCurrency) throws SQLException {
    String updateCharacterCurrency = "UPDATE CharacterCurrency SET currencyTotalAmount = ?, currencyEarnedThisWeek = ? WHERE characterID = ? AND currencyID = ?";
    Connection connection = null;
    PreparedStatement updateStmt = null;

    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateCharacterCurrency);

      updateStmt.setDouble(1, characterCurrency.getCurrencyTotalAmount());
      updateStmt.setDouble(2, characterCurrency.getCurrencyEarnedThisWeek());
      updateStmt.setInt(3, characterCurrency.getCharacter().getCharacterID());
      updateStmt.setInt(4, characterCurrency.getCurrency().getCurrencyID());

      updateStmt.executeUpdate();
      return characterCurrency;
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

  // Delete CharacterCurrency by characterID and currencyID
  public void delete(int characterID, int currencyID) throws SQLException {
    String deleteCharacterCurrency = "DELETE FROM CharacterCurrency WHERE characterID = ? AND currencyID = ?";
    Connection connection = null;
    PreparedStatement deleteStmt = null;

    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCharacterCurrency);

      deleteStmt.setInt(1, characterID);
      deleteStmt.setInt(2, currencyID);
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

  // Get all CharacterCurrencies by characterID
  public List<CharacterCurrency> getByCharacterID(int characterID) throws SQLException {
    String selectCharacterCurrencies = "SELECT * FROM CharacterCurrency WHERE characterID = ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet resultSet = null;
    List<CharacterCurrency> characterCurrencies = new ArrayList<>();

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCharacterCurrencies);
      selectStmt.setInt(1, characterID);

      resultSet = selectStmt.executeQuery();
      while (resultSet.next()) {
        Currency currency = CurrencyDao.getInstance().getByID(resultSet.getInt("currencyID"));
        Characters character = CharactersDao.getInstance().getCharacterByID(characterID);

        double totalAmount = resultSet.getDouble("currencyTotalAmount");
        double earnedThisWeek = resultSet.getDouble("currencyEarnedThisWeek");

        CharacterCurrency characterCurrency = new CharacterCurrency(character, currency, totalAmount, earnedThisWeek);
        characterCurrencies.add(characterCurrency);
      }
      return characterCurrencies;
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
