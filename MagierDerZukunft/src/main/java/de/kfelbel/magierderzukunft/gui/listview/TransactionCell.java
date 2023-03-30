package de.kfelbel.magierderzukunft.gui.listview;

import de.kfelbel.magierderzukunft.model.Transaction;
import de.kfelbel.magierderzukunft.settings.AppTexts;
import javafx.scene.control.ListCell;

/**
 * Dient als Bauplan für Zellen, die in der ListView für Transaktionen angezeigt werden sollen.
 */
public class TransactionCell extends ListCell<Transaction> {
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    @Override
    protected void updateItem(Transaction transactionToShow, boolean isEmpty) {
        super.updateItem(transactionToShow, isEmpty);

        if (isEmpty || transactionToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(String.format(AppTexts.TRANSACTION_LISTVIEW_FORMAT_STRING,
                    transactionToShow.getCoinToBuy(), transactionToShow.getPricePerCoin(),
                    transactionToShow.getAmountUSDT(), transactionToShow.getAmountCoin()));
        }
    }
    //endregion
}
