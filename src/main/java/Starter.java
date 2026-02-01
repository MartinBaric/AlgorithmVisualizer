import gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application {

    @Override
    public void start(Stage stage) {
        Gui.setMainGui(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
