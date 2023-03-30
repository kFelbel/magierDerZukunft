package de.kfelbel.magierderzukunft.logic.db;

import de.kfelbel.magierderzukunft.model.Coin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Objekt für Coin-Objekte.
 *
 * Stellt Methoden zur Verfügung, um Coins in die Datenbanktabelle coins zu schreiben und
 * diese auch wieder auszulesen.
 */
public class DaoCoins implements Dao<Coin> {
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    //endregion

    //region Konstruktoren
    protected DaoCoins() {

    }
    //endregion

    //region Methoden
    @Override
    public void create(Connection dbConnection, Coin coin) {
        PreparedStatement statement = null;

        try {
            statement = dbConnection.prepareStatement("INSERT INTO coins (name, symbol, price, founder) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, coin.getName());
            statement.setString(2, coin.getSymbol());
            statement.setDouble(3, coin.getPrice());
            statement.setString(4, coin.getFounder());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int insertId = generatedKeys.getInt("insert_id");
                coin.setId(insertId);
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

    }
    @Override
    public List<Coin> readAll(Connection dbConnection) {
        List<Coin> coins = new ArrayList<>();

        Statement statement = null;

        try {

            statement = dbConnection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM coins");

            while (resultSet.next()) {
                Coin coin = new Coin(
                        resultSet.getString("name"),
                        resultSet.getString("symbol"),
                        resultSet.getDouble("price"),
                        resultSet.getString("founder")
                        );
                coin.setId(resultSet.getInt("_id"));

                coins.add(coin);
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
        return coins;
    }

    @Override
    public void update(Connection dbConnection, Coin coin){
        PreparedStatement statement = null;

        try {

            statement = dbConnection.prepareStatement("UPDATE coins SET name=?, symbol=?, price=?, founder=? WHERE _id=?");
            statement.setString(1, coin.getName());
            statement.setString(2, coin.getSymbol());
            statement.setDouble(3, coin.getPrice());
            statement.setString(4, coin.getFounder());
            statement.setInt(5, coin.getId());

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

    @Override
    public void delete(Connection dbConnection, Coin coin) {
        PreparedStatement statement = null;

        try {
            statement = dbConnection.prepareStatement("DELETE FROM coins WHERE _id=?");
            statement.setInt(1, coin.getId());

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
