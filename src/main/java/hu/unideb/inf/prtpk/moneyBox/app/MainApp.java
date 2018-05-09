package hu.unideb.inf.prtpk.moneyBox.app;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Az alkalmazás futtatása.
 */
public class MainApp extends Application {

    /**
     * Adatbázis jelszó.
     */
    private static String DATABASE_PASSWORD;

    /**
     * JavaFX.
     */
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
    }

    /**
     * <pre>Main metódus.</pre>
     * @param args STDIN
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            DATABASE_PASSWORD = args[0];
        } else {
            System.exit(1);
        }

        System.out.println("hello");

        launch();
    }
}
