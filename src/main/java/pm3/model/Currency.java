package pm3.model;

public class Currency {
  private int currencyID;
  private String currencyName;
  private double currencyMaxAmount;
  private double currencyWeeklyCap;

  public Currency() {
  }

  public Currency(int currencyID, String currencyName, double currencyMaxAmount, double currencyWeeklyCap) {
    this.currencyID = currencyID;
    this.currencyName = currencyName;
    this.currencyMaxAmount = currencyMaxAmount;
    this.currencyWeeklyCap = currencyWeeklyCap;
  }

  public int getCurrencyID() {
    return currencyID;
  }

  public void setCurrencyID(int currencyID) {
    this.currencyID = currencyID;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public double getCurrencyMaxAmount() {
    return currencyMaxAmount;
  }

  public void setCurrencyMaxAmount(double currencyMaxAmount) {
    this.currencyMaxAmount = currencyMaxAmount;
  }

  public double getCurrencyWeeklyCap() {
    return currencyWeeklyCap;
  }

  public void setCurrencyWeeklyCap(double currencyWeeklyCap) {
    this.currencyWeeklyCap = currencyWeeklyCap;
  }
}
