package de.kfelbel.magierderzukunft.gui;

import de.kfelbel.magierderzukunft.Main;
import de.kfelbel.magierderzukunft.settings.AppTexts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Singleton welcher zuständig ist eine einzige Instanz bereitzustellen und das Programm abzurufen.
 */
public class SceneManager {
    //region Konstanten
    //endregion Konstanten

    //region Attribute
    private static SceneManager instance;

    private Stage mainStage;

    //endregion

    //region Konstruktoren
    private SceneManager() {

    }
    //endregion

    //region Methoden
    public static synchronized SceneManager getInstance() {
        if (instance == null) instance = new SceneManager();
        return instance;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
        this.mainStage.setTitle(AppTexts.MAINSTAGE_TITLE);
        loadMainStage();
    }

    public void loadMainStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main/main-view.fxml"));
            Scene mainScene = new Scene(fxmlLoader.load());
            mainStage.setScene(mainScene);
            mainStage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    //endregion
}
