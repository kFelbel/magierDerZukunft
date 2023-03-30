package de.kfelbel.magierderzukunft.test;

import de.kfelbel.magierderzukunft.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * Testmain zum Starten.
 */
public class TestMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main/main-view.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Testfenster");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}