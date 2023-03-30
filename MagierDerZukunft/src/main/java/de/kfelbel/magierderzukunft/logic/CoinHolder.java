package de.kfelbel.magierderzukunft.logic;

import de.kfelbel.magierderzukunft.logic.db.DbManager;
import de.kfelbel.magierderzukunft.model.Coin;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.List;

/**
 * Ist als Singleton implementiert und stellt die Coins als Observable List zur Verfügung.
 */
public class CoinHolder {
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    private static CoinHolder instance;

    private ObservableList<Coin> coins;
    private List<Coin> normalCoinList;
    //endregion

    //region Konstruktoren
    private CoinHolder() {
        normalCoinList = DbManager.getInstance().readAllCoins();

        coins = FXCollections.observableArrayList(new Callback<Coin, Observable[]>() {
            @Override
            public Observable[] call(Coin coin) {
                return new Observable[] {coin.nameProperty(), coin.symbolProperty(),
                        coin.priceProperty(), coin.founderProperty()};
            }
        });

        coins.addAll(normalCoinList);

        CoinListChangeListener listener = new CoinListChangeListener();
        coins.addListener(listener);
    }

    public static synchronized CoinHolder getInstance() {
        if (instance == null) instance = new CoinHolder();
        return instance;
    }



    //endregion

    //region Methoden
    public ObservableList<Coin> getCoins() {
        return coins;
    }
    //endregion
}
