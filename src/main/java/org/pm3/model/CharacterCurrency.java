package org.pm3.model;

public class CharacterCurrency {
  private Characters character;
  private Currency currency;
  private double currencyTotalAmount;
  private double currencyEarnedThisWeek;

  public CharacterCurrency() {
  }

  public CharacterCurrency(Characters character, Currency currency, double currencyTotalAmount, double currencyEarnedThisWeek) {
    this.character = character;
    this.currency = currency;
    this.currencyTotalAmount = currencyTotalAmount;
    this.currencyEarnedThisWeek = currencyEarnedThisWeek;
  }

  public Characters getCharacter() {
    return character;
  }

  public void setCharacter(Characters character) {
    this.character = character;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public double getCurrencyTotalAmount() {
    return currencyTotalAmount;
  }

  public void setCurrencyTotalAmount(double currencyTotalAmount) {
    this.currencyTotalAmount = currencyTotalAmount;
  }

  public double getCurrencyEarnedThisWeek() {
    return currencyEarnedThisWeek;
  }

  public void setCurrencyEarnedThisWeek(double currencyEarnedThisWeek) {
    this.currencyEarnedThisWeek = currencyEarnedThisWeek;
  }
}
