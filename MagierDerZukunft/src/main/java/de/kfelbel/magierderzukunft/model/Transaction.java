package de.kfelbel.magierderzukunft.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Modellklasse für Transaktionen.
 */
public class Transaction {
    //region Konstanten
    public static final String DEF_STRING_VALUE = "noValueSetYet";
    public static final double DEF_DOUBLE_VALUE = -1.0;
    //endregion Konstanten

    //region Attribute
    private int id;
    private StringProperty coinToBuy;
    private DoubleProperty pricePerCoin;
    private DoubleProperty amountUSDT;
    private DoubleProperty amountCoin;
    //endregion

    //region Konstruktoren
    public Transaction() {
        coinToBuy = new SimpleStringProperty(DEF_STRING_VALUE);
        pricePerCoin = new SimpleDoubleProperty(DEF_DOUBLE_VALUE);
        amountUSDT = new SimpleDoubleProperty(DEF_DOUBLE_VALUE);
        amountCoin = new SimpleDoubleProperty(DEF_DOUBLE_VALUE);
    }

    public Transaction(String coinToBuy, double pricePerCoin, double amountUSDT, double amountCoin) {
        this.coinToBuy = new SimpleStringProperty(coinToBuy);
        this.pricePerCoin = new SimpleDoubleProperty(pricePerCoin);
        this.amountUSDT = new SimpleDoubleProperty(amountUSDT);
        this.amountCoin = new SimpleDoubleProperty(amountCoin);
    }

    //endregion

    //region Methoden

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoinToBuy() {
        return coinToBuy.get();
    }

    public StringProperty coinToBuyProperty() {
        return coinToBuy;
    }

    public void setCoinToBuy(String coinToBuy) {
        this.coinToBuy.set(coinToBuy);
    }

    public double getPricePerCoin() {
        return pricePerCoin.get();
    }

    public DoubleProperty pricePerCoinProperty() {
        return pricePerCoin;
    }

    public void setPricePerCoin(double pricePerCoin) {
        this.pricePerCoin.set(pricePerCoin);
    }

    public double getAmountUSDT() {
        return amountUSDT.get();
    }

    public DoubleProperty amountUSDTProperty() {
        return amountUSDT;
    }

    public void setAmountUSDT(double amountUSDT) {
        this.amountUSDT.set(amountUSDT);
    }

    public double getAmountCoin() {
        return amountCoin.get();
    }

    public DoubleProperty amountCoinProperty() {
        return amountCoin;
    }

    public void setAmountCoin(double amountCoin) {
        this.amountCoin.set(amountCoin);
    }

    //endregion
}
