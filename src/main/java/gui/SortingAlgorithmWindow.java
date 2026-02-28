package gui;

import controller.GUIController;
import csvInput.CSVLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sortingAlgorithms.enums.SortingAlgorithmType;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static animation.SortingAnimation.markBar;

public class SortingAlgorithmWindow {
    private static final int WIDTH_SORTING = 900;
    private static final int HEIGHT_SORTING = 600;

    private static final int WIDTH_ALGORITHM_WINDOW = 600;
    private static final int HEIGHT_ALGORITHM_WINDOW = 400;

    private static final float MIN_VALUE = 0.1f;
    private static final float MAX_VALUE = 3.0f;

    private static final int SPACING = 15;

    GUIController guiController;

    public SortingAlgorithmWindow(GUIController guiController) {
        this.guiController = guiController;
    }

    public void showSortingWindow(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH_SORTING, HEIGHT_SORTING);

        List<Integer> numbers = CSVLoader.loadCSVInput();
        HBox verticalBars = visualizeBars(numbers);

        ScrollPane algorithmWindow = new ScrollPane(verticalBars);
        algorithmWindow.setFitToHeight(true);
        algorithmWindow.setPannable(true);
        algorithmWindow.setPrefViewportHeight(WIDTH_ALGORITHM_WINDOW);
        algorithmWindow.setPrefViewportHeight(HEIGHT_ALGORITHM_WINDOW);

        algorithmWindow.setStyle("-fx-border-color: black; -fx-border-width: 2;");

        BorderPane.setMargin(algorithmWindow, new Insets(20, 20, 20, 20));

        showAlgorithmPanel(root, numbers, verticalBars);

        root.setCenter(algorithmWindow);

        stage.setScene(scene);
    }

    public void showAlgorithmPanel(BorderPane root, List<Integer> numbers, HBox verticalBars) {
        RadioButton bubbleSort = new RadioButton(SortingAlgorithmType.BUBBLESORT.getName());
        RadioButton insertSort = new RadioButton(SortingAlgorithmType.INSERTSORT.getName());
        RadioButton selectSort = new RadioButton(SortingAlgorithmType.SELECTSORT.getName());

        bubbleSort.setUserData(SortingAlgorithmType.BUBBLESORT);
        insertSort.setUserData(SortingAlgorithmType.INSERTSORT);
        selectSort.setUserData(SortingAlgorithmType.SELECTSORT);

        ToggleGroup group = new ToggleGroup();
        bubbleSort.setToggleGroup(group);
        insertSort.setToggleGroup(group);
        selectSort.setToggleGroup(group);

        Slider slider = new Slider(MIN_VALUE, MAX_VALUE, (MAX_VALUE + MIN_VALUE) / 2);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.prefWidth(400);
        Button start = new Button("Start");
        Button back = new Button("Back");

        start.setOnAction(e -> guiController.startSorting(group.getSelectedToggle(), numbers,
                verticalBars, slider));
        back.setOnAction(e -> guiController.showMainMenu());

        VBox algorithms = new VBox(SPACING, bubbleSort, insertSort, selectSort);
        VBox controlPanel = new VBox(SPACING, slider, start, algorithms, back);
        controlPanel.setPadding(new Insets(20));
        controlPanel.setAlignment(Pos.TOP_CENTER);

        root.setRight(controlPanel);
    }

    private static HBox visualizeBars(List<Integer> numbers) {
        HBox box = new HBox(2); // 2px spacing between bars
        box.setAlignment(Pos.BOTTOM_LEFT); // bars grow upwards

        // optional: find max to scale bars nicely
        int max = Collections.max(numbers);

        ImagePattern brickPattern = getBrickImagePattern();

        for (int value : numbers) {
            Rectangle bar = new Rectangle();
            bar.setWidth(20); // width of each bar
            bar.setHeight((double) value / max * 300); // scale height to max 300 px
            bar.setFill(brickPattern);
            markBar(bar, false);

            box.getChildren().add(bar);
        }

        return box;
    }

    private static ImagePattern getBrickImagePattern() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("moosybrick.jpg");
        if (is == null) {
            throw new RuntimeException("moosybrick.jpg not found in classpath!");
        }

        Image brickImage = new Image(is);
        return new ImagePattern(brickImage, 0, 0, 20, 30, false);
    }
}
