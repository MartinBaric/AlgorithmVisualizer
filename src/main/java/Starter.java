import controller.GUIController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application {

    @Override
    public void start(Stage stage) {
        GUIController guiController = new GUIController(stage);
        guiController.showMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
