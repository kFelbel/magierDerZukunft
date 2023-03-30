package de.kfelbel.magierderzukunft.gui.listview;

import de.kfelbel.magierderzukunft.model.Transaction;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *  * Erzeugt {@link TransactionCell}s für die und anhand der ListView.
 */
public class TransactionCellFactory implements Callback<ListView<Transaction>, ListCell<Transaction>> {
    @Override
    public ListCell<Transaction> call(ListView<Transaction> TransactionCell) {
        return new TransactionCell();
    }
//region Konstanten
    //endregion Konstanten

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    //endregion
}
