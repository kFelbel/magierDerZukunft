package de.kfelbel.magierderzukunft;

import de.kfelbel.magierderzukunft.gui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneManager.getInstance().setMainStage(primaryStage);


    }


    public static void main(String[] args) {
        launch(args);
    }
}