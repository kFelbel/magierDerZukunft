package de.kfelbel.magierderzukunft.logic.db;

import de.kfelbel.magierderzukunft.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Objekt für Transaktion-Objekte
 *
 *  Stellt Methoden zur Verfügung, um Transaktionen in die Datenbanktabelle transactions zu schreiben und
 *  diese auch wieder auszulesen.
 */
public class DaoTransactions implements Dao<Transaction>{
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    //endregion

    //region Konstruktoren
    protected DaoTransactions() {
    }
    //endregion

    //region Methoden
    @Override
    public void create(Connection dbConnection, Transaction transaction) {
        PreparedStatement statement = null;

        try {
            statement = dbConnection.prepareStatement("INSERT INTO transactions(coinToBuy, pricePerCoin, amountUSDT, " +
                    "amountCoin) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, transaction.getCoinToBuy());
            statement.setDouble(2, transaction.getPricePerCoin());
            statement.setDouble(3, transaction.getAmountUSDT());
            statement.setDouble(4, transaction.getAmountCoin());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int insertId = generatedKeys.getInt("insert_id");
                transaction.setId(insertId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Transaction> readAll(Connection dbConnection) {
        List<Transaction> transactions = new ArrayList<>();

        Statement statement = null;

        try {

            statement = dbConnection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions");

            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getString("coinToBuy"),
                        resultSet.getDouble("pricePerCoin"),
                        resultSet.getDouble("amountUSDT"),
                        resultSet.getDouble("amountCoin")
                        );
                transaction.setId(resultSet.getInt("_id"));

                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                dbConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactions;
    }

    @Override
    public void update(Connection dbConnection, Transaction transaction) {
        PreparedStatement statement = null;

        try {

            statement = dbConnection.prepareStatement("UPDATE transactions SET coinToBuy=?, pricePerCoin=?," +
                    "amountUSDT=?, amountCoin=? WHERE _id=?");
            statement.setString(1, transaction.getCoinToBuy());
            statement.setDouble(2, transaction.getPricePerCoin());
            statement.setDouble(3, transaction.getAmountUSDT());
            statement.setDouble(4, transaction.getAmountCoin());
            statement.setInt(5, transaction.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                dbConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Connection dbConnection, Transaction transaction) {
        PreparedStatement statement = null;

        try {

            statement = dbConnection.prepareStatement("DELETE FROM transactions WHERE _id = ?");
            statement.setInt(1, transaction.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                dbConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    //endregion
}
