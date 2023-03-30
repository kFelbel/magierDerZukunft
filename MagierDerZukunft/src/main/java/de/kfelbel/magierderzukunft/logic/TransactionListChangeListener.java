package de.kfelbel.magierderzukunft.logic;

import de.kfelbel.magierderzukunft.logic.db.DbManager;
import de.kfelbel.magierderzukunft.model.Transaction;
import de.kfelbel.magierderzukunft.settings.AppTexts;
import javafx.collections.ListChangeListener;

/**
 * List Change Listener für die Transaktionen-Liste.
 */
public class TransactionListChangeListener implements ListChangeListener<Transaction> {
//region Konstanten
    //endregion Konstanten

    //region Attribute


    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    @Override
    public void onChanged(Change<? extends Transaction> change) {
        System.out.println(change);

        while (change.next()) {

            if (change.wasReplaced()) {

                System.out.println(AppTexts.TXT_REPLACED);
 //               System.out.println(change.getAddedSubList().get(0));
                Transaction transactionToUpdate = change.getAddedSubList().get(0);
                DbManager.getInstance().updateDataRecord(transactionToUpdate);

            } else if (change.wasRemoved()) {

                System.out.println(AppTexts.TXT_REMOVE);
//                System.out.println(change.getRemoved().get(0));
                Transaction transactionToRemove = change.getRemoved().get(0);
                DbManager.getInstance().deleteDataRecord(transactionToRemove);

            } else if (change.wasAdded()) {

                System.out.println("Es wurde hinzugefügt:");
//                System.out.println(change.getAddedSubList().get(0));
                Transaction transactionToInsert = change.getAddedSubList().get(0);
                DbManager.getInstance().insertDataRecord(transactionToInsert);


            } else if (change.wasUpdated()) {

                System.out.println("Es wurde aktualisiert:");
                System.out.println(change.getList().get(change.getFrom()));

            }
        }
    }
    //endregion
}
