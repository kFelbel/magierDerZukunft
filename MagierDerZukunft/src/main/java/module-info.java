module de.kfelbel.magierderzukunft {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports de.kfelbel.magierderzukunft.test to javafx.graphics;
    opens de.kfelbel.magierderzukunft to javafx.fxml;
    exports de.kfelbel.magierderzukunft;
    exports de.kfelbel.magierderzukunft.gui;
    opens de.kfelbel.magierderzukunft.gui to javafx.fxml;
}