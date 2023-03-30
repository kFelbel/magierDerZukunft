package de.kfelbel.magierderzukunft.gui.listview;

import de.kfelbel.magierderzukunft.model.Coin;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Erzeugt {@link CoinCell}s für die und anhand der ListView.
 */
public class CoinCellFactory implements Callback<ListView<Coin>, ListCell<Coin>> {
//region Konstanten
    //endregion Konstanten

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    @Override
    public ListCell<Coin> call(ListView<Coin> coinListView) {
        return new CoinCell();
    }
    //endregion
}
