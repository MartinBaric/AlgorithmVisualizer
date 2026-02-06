package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    private static final int WIDTH_MAIN_GUI = 300;
    private static final int HEIGHT_MAIN_GUI = 200;

    private static final int BUTTON_SIZE = 200;

    private static final int SPACING = 15;


    public static void setMainGui(Stage stage) {
        VBox root = new VBox(5);

        Button sortingAlgorithms = new Button("Sorting Algorithms");
        Button exit = new Button("Exit");

        sortingAlgorithms.setPrefWidth(BUTTON_SIZE);
        exit.setPrefWidth(BUTTON_SIZE);

        sortingAlgorithms.setOnAction(e -> AlgorithmMenu.visualizationScene(stage));
        exit.setOnAction(e -> stage.close());

        root.setAlignment(Pos.CENTER);
        root.setSpacing(SPACING);
        root.getChildren().addAll(sortingAlgorithms, exit);

        Scene scene = new Scene(root, WIDTH_MAIN_GUI, HEIGHT_MAIN_GUI);
        stage.setScene(scene);
        stage.show();
    }
}
