package gui;

import csvInput.CSVLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.SortingAlgorithm;
import sortingAlgorithms.enums.SortingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gui {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;
    private static final int SPACING = 15;

    public static void setMainGui(Stage stage) {
        VBox root = new VBox();

        Button sortingAlgorithms = new Button("Sorting Alggrithms");
        Button exit = new Button("Exit");

        sortingAlgorithms.setOnAction(e -> visualizationScene(stage));
        exit.setOnAction(e -> stage.close());

        root.getChildren().addAll(sortingAlgorithms, exit);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private static void visualizationScene(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        List<Integer> numbers = CSVLoader.loadCSVInput();
        HBox verticalBars = visualizeBars(numbers);

        root.setCenter(verticalBars);

        RadioButton bubbleSort = new RadioButton(SortingType.BUBBLESORT.getName());

        ToggleGroup group = new ToggleGroup();
        bubbleSort.setToggleGroup(group);

        Button start = new Button("Start");
        Button back = new Button("Back");

        start.setOnAction(e -> startAlgorithm(group.getSelectedToggle(), numbers,
                verticalBars));
        back.setOnAction(e -> setMainGui(stage));

        VBox controlPanel = new VBox(SPACING, start, bubbleSort, back);
        controlPanel.setPadding(new Insets(20));
        controlPanel.setAlignment(Pos.TOP_CENTER);

        root.setRight(controlPanel);

        stage.setScene(scene);
    }

    private static HBox visualizeBars(List<Integer> numbers) {
        HBox box = new HBox(2); // 2px spacing between bars
        box.setAlignment(Pos.BOTTOM_LEFT); // bars grow upwards

        // optional: find max to scale bars nicely
        int max = Collections.max(numbers);

        for (int value : numbers) {
            Rectangle bar = new Rectangle();
            bar.setWidth(20); // width of each bar
            bar.setHeight((double) value / max * 300); // scale height to max 300 px
            bar.setFill(Color.DODGERBLUE);

            box.getChildren().add(bar);
        }

        return box;
    }

    private static void startAlgorithm(Toggle algorithmToggle, List<Integer> numbers,
                                       HBox barsContainer) {
        Timeline timeline = new Timeline();

        List<Rectangle> bars = new ArrayList<>(barsContainer.getChildren().stream()
                .map(r -> (Rectangle) r)
                .toList());

        SwapListener<Integer> swapListener = (i, j) -> {
            timeline.getKeyFrames().add(new KeyFrame(
                    Duration.millis((timeline.getKeyFrames().size() + 1) * 1000),
                    ev -> {
                        double h1 = bars.get(i).getHeight();
                        double h2 = bars.get(j).getHeight();
                        bars.get(i).setHeight(h2);
                        bars.get(j).setHeight(h1);

                        bars.get(i).setFill(Color.RED);
                        bars.get(j).setFill(Color.RED);
                    }
            ));
        };

        SortingAlgorithm<Integer> bubbleSort = new BubbleSort<>(numbers);
        bubbleSort.sort(swapListener);

        timeline.play();
    }
}
