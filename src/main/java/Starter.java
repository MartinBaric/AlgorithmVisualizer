import gui.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application {

    @Override
    public void start(Stage stage) {
        MainMenu.setMainGui(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
