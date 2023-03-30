package de.kfelbel.magierderzukunft.logic;

import de.kfelbel.magierderzukunft.logic.db.DbManager;
import de.kfelbel.magierderzukunft.model.Transaction;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.List;

/**
 * Ist als Singleton implementiert und stellt die Transaktionen als Observable List zur Verfügung.
 */
public class TransactionHolder {
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    private static TransactionHolder instance;

    private ObservableList<Transaction> transactions;



    private List<Transaction> normalTransactionList;


    //endregion

    //region Konstruktoren
    public static synchronized TransactionHolder getInstance() {
        if (instance == null) instance = new TransactionHolder();
        return instance;
    }


    private TransactionHolder() {
        normalTransactionList = DbManager.getInstance().readAllTransactions();

        transactions = FXCollections.observableArrayList(new Callback<Transaction, Observable[]>() {
            @Override
            public Observable[] call(Transaction transaction) {
                return new Observable[] {
                        transaction.coinToBuyProperty(), transaction.pricePerCoinProperty(),
                        transaction.amountUSDTProperty(), transaction.amountCoinProperty()
                };
            }
        });

        transactions.addAll(normalTransactionList);

        TransactionListChangeListener listener = new TransactionListChangeListener();
        transactions.addListener(listener);
    }

    //endregion

    //region Methoden


    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    //endregion
}
