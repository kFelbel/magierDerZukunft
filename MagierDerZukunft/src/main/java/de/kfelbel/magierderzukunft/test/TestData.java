package de.kfelbel.magierderzukunft.test;

import de.kfelbel.magierderzukunft.model.Coin;
import de.kfelbel.magierderzukunft.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Generiert Testdaten für das Programm.
 */
public class TestData {
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden

    public static List<Coin> getTestCryptos() {
        List<Coin> testCoins = new ArrayList<>();

        testCoins.add(new Coin("Bitcoin", "BTC", 22_000, "Satoshi"));
        testCoins.add(new Coin("Ethereum", "ETH", 1_400, "Nikolai"));
        testCoins.add(new Coin("Ripple", "XRP", 0.36, "Peter"));
        testCoins.add(new Coin("Verasity", "VRA", 0.0064, "Shabab"));

        return testCoins;
    }

    public static List<Transaction> testTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("Bitcoin", 24000, 12000, 0.5));
        transactions.add(new Transaction("Ethereum", 1600, 1600, 1));
        transactions.add(new Transaction("Bitcoin", 24000, 24000, 1));


        return transactions;
    }
    //endregion
}
