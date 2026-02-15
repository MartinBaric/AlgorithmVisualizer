package gui;

import controller.GUIController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuWindow {
    private static final int WIDTH_MAIN_GUI = 300;
    private static final int HEIGHT_MAIN_GUI = 200;

    private static final int BUTTON_SIZE = 200;

    private static final int SPACING = 15;

    private final GUIController guiController;

    public MainMenuWindow(GUIController guiController) {
        this.guiController = guiController;
    }

    public void showMainWindow(Stage stage) {
        VBox root = new VBox(5);

        Button sortingAlgorithms = new Button("Sorting Algorithms");
        Button exit = new Button("Exit");

        sortingAlgorithms.setPrefWidth(BUTTON_SIZE);
        exit.setPrefWidth(BUTTON_SIZE);

        sortingAlgorithms.setOnAction(e -> guiController.showSortingAlgorithmMenu());
        exit.setOnAction(e -> guiController.close());

        root.setAlignment(Pos.CENTER);
        root.setSpacing(SPACING);
        root.getChildren().addAll(sortingAlgorithms, exit);

        Scene scene = new Scene(root, WIDTH_MAIN_GUI, HEIGHT_MAIN_GUI);
        stage.setScene(scene);
        stage.show();
    }
}
