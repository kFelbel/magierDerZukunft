package de.kfelbel.magierderzukunft.logic.db;

import de.kfelbel.magierderzukunft.model.Coin;
import de.kfelbel.magierderzukunft.model.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Baut Verbindungen zur Datenbank auf und hat Zugriff auf die jeweiligen DAOS
 * der Modellklasse Coin.
 */
public class DbManager {
    //region Konstanten
    public static final String URL_PREFIX = "jdbc:mariadb://";
    public static final String SERVER_IP = "localhost";
    public static final String DB_NAME = "/magierderzukunft";


    public static final String URL = URL_PREFIX + SERVER_IP + DB_NAME;

    public static final String USERNAME = "kfelbel";
    public static final String PASSWORD = "1234";
    //endregion Konstanten

    //region Attribute
    private static DbManager instance;

    private static DaoCoins daoCoins;

    private static DaoTransactions daoTransactions;
    //endregion

    //region Konstruktoren
    private DbManager() {
        daoCoins = new DaoCoins();
        daoTransactions = new DaoTransactions();
    }

    //endregion

    //region Methoden
    public static synchronized DbManager getInstance() {
        if (instance == null) instance = new DbManager();
        return instance;
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void insertDataRecord(Object object) {
        if (object instanceof Coin coin) {
            daoCoins.create(getConnection(), coin);
        } else if (object instanceof Transaction transaction) {
            daoTransactions.create(getConnection(), transaction);
        }
    }

    public List<Coin> readAllCoins() { return daoCoins.readAll(getConnection());}

    public List<Transaction> readAllTransactions() { return daoTransactions.readAll(getConnection());}

    public void updateDataRecord(Object object) {
        if (object instanceof Coin coin) {
            daoCoins.update(getConnection(), coin);
        } else if (object instanceof Transaction transaction) {
            daoTransactions.update(getConnection(), transaction);
        }
    }

    public void deleteDataRecord(Object object) {
        if (object instanceof Coin coin) {
            daoCoins.delete(getConnection(), coin);
        } else if (object instanceof Transaction transaction) {
            daoTransactions.delete(getConnection(), transaction);
        }
    }
    //endregion
}
