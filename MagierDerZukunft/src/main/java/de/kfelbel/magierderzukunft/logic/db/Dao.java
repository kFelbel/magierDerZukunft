package de.kfelbel.magierderzukunft.logic.db;

import java.sql.Connection;
import java.util.List;

/**
 * Interface, welches die Crud-Funktionalität für eine ModellKlasse bereitstellt.
 * @param <T> wiederverwendbare Implementierung der Schnittstelle für unterschiedliche Datentypen.
 */
public interface Dao<T>{

    void create(Connection dbConnection, T object);

    List<T> readAll(Connection dbConnection);

    void update(Connection dbConnection, T object);

    void delete(Connection dbConnection, T object);




}
