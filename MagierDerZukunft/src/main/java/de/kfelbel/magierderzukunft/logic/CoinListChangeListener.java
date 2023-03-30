package de.kfelbel.magierderzukunft.logic;

import de.kfelbel.magierderzukunft.logic.db.DbManager;
import de.kfelbel.magierderzukunft.model.Coin;
import de.kfelbel.magierderzukunft.settings.AppTexts;
import javafx.collections.ListChangeListener;

/**
 * List Change Listener für die CoinListe.
 */
public class CoinListChangeListener implements ListChangeListener<Coin> {
    //region Konstanten
        //endregion Konstanten

        //region Attribute
        //endregion

        //region Konstruktoren
    @Override
    public void onChanged(Change<? extends Coin> change) {
        System.out.println(change);

        while (change.next()) {

            if (change.wasReplaced()) {

                System.out.println(AppTexts.TXT_REPLACED);
                Coin coinToUpdate = change.getAddedSubList().get(0);
                DbManager.getInstance().updateDataRecord(coinToUpdate);

            } else if (change.wasRemoved()) {

                System.out.println(AppTexts.TXT_REMOVE);
                Coin coinToRemove = change.getRemoved().get(0);
                DbManager.getInstance().deleteDataRecord(coinToRemove);

            } else if (change.wasAdded()) {
                System.out.println(AppTexts.TXT_ADDED);
                Coin coinToInsert = change.getAddedSubList().get(0);
                DbManager.getInstance().insertDataRecord(coinToInsert);

            } else if (change.wasUpdated()) {

                System.out.println(AppTexts.TXT_UPDATE);
                System.out.println(change.getList().get(change.getFrom()));

            }
        }
        //endregion

        //region Methoden

        //endregion
    }
}
