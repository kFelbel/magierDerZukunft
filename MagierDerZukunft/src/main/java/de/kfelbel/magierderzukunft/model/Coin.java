package de.kfelbel.magierderzukunft.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ModellKlasse für Coins.
 */
public class Coin {

        //region Konstanten
        public static final String DEF_STRING_VALUE = "noValueSetYet";
        public static final double DEF_DOUBLE_VALUE = -1.0;
        //endregion

        //region Attribute
        private int id;
        private StringProperty name;
        private StringProperty symbol;
        private DoubleProperty price;
        private StringProperty founder;
        //endregion

        //region Konstruktoren

        public Coin() {
            name = new SimpleStringProperty(DEF_STRING_VALUE);
            symbol = new SimpleStringProperty(DEF_STRING_VALUE);
            price = new SimpleDoubleProperty(DEF_DOUBLE_VALUE);
            founder = new SimpleStringProperty(DEF_STRING_VALUE);
        }

        public Coin(String name, String symbol, double price, String founder) {
            this.name = new SimpleStringProperty(name);
            this.symbol = new SimpleStringProperty(symbol);
            this.price = new SimpleDoubleProperty(price);
            this.founder = new SimpleStringProperty(founder);
        }

        //endregion

        //region Methoden

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getSymbol() {
            return symbol.get();
        }

        public StringProperty symbolProperty() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol.set(symbol);
        }

        public double getPrice() {
            return price.get();
        }

        public DoubleProperty priceProperty() {
            return price;
        }

        public void setPrice(double price) {
            this.price.set(price);
        }

        public String getFounder() {
            return founder.get();
        }

        public StringProperty founderProperty() {
            return founder;
        }

        public void setFounder(String founder) {
            this.founder.set(founder);
        }

        @Override
        public String toString() {
            return
                  "(" + symbol.get() +  ") " + name.get();
        }

        //endregion
    }



