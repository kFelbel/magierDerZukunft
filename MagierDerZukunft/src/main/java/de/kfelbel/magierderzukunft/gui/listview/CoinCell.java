package de.kfelbel.magierderzukunft.gui.listview;

import de.kfelbel.magierderzukunft.model.Coin;
import de.kfelbel.magierderzukunft.settings.AppTexts;
import javafx.scene.control.ListCell;

/**
 * Dient als Bauplan für Zellen, die in der ListView für Coins angezeigt werden sollen.
 */
public class CoinCell extends ListCell<Coin> {
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden
    @Override
    protected void updateItem(Coin coinToShow, boolean isEmpty) {
        super.updateItem(coinToShow, isEmpty);

        if (isEmpty || coinToShow == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(String.format(AppTexts.COIN_LISTVIE_FORMAT_STRING,
                    coinToShow.getName(), coinToShow.getSymbol(), coinToShow.getPrice(), coinToShow.getFounder()));

        }
    }
    //endregion
}
