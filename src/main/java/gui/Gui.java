package gui;

import csvInput.CSVLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.SortingAlgorithm;
import sortingAlgorithms.functionalInterfaces.SwapListener;
import sortingAlgorithms.enums.SortingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gui {
    private static final int WIDTH_SORTING = 900;
    private static final int HEIGHT_SORTING = 600;

    private static final int WIDTH_MAIN_GUI = 300;
    private static final int HEIGHT_MAIN_GUI = 200;

    private static final int SPACING = 15;

    private static final float MIN_VALUE = 0.1f;
    private static final float MAX_VALUE = 3.0f;

    public static void setMainGui(Stage stage) {
        VBox root = new VBox(5);

        Button sortingAlgorithms = new Button("Sorting Algorithms");
        Button exit = new Button("Exit");

        sortingAlgorithms.setOnAction(e -> visualizationScene(stage));
        exit.setOnAction(e -> stage.close());

        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(SPACING);
        root.getChildren().addAll(sortingAlgorithms, exit);

        Scene scene = new Scene(root, WIDTH_MAIN_GUI, HEIGHT_MAIN_GUI);
        stage.setScene(scene);
        stage.show();
    }

    private static void visualizationScene(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, WIDTH_SORTING, HEIGHT_SORTING);

        List<Integer> numbers = CSVLoader.loadCSVInput();
        HBox verticalBars = visualizeBars(numbers);

        root.setCenter(verticalBars);

        RadioButton bubbleSort = new RadioButton(SortingType.BUBBLESORT.getName());

        ToggleGroup group = new ToggleGroup();
        bubbleSort.setToggleGroup(group);

        Slider slider = new Slider(MIN_VALUE, MAX_VALUE, (MAX_VALUE + MIN_VALUE) / 2);
        slider.setOrientation(Orientation.HORIZONTAL);
        Button start = new Button("Start");
        Button back = new Button("Back");

        start.setOnAction(e -> startAlgorithm(group.getSelectedToggle(), numbers,
                verticalBars, slider));
        back.setOnAction(e -> setMainGui(stage));

        VBox controlPanel = new VBox(SPACING, slider, start, bubbleSort, back);
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
                                       HBox barsContainer, Slider slider) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        List<Rectangle> bars = new ArrayList<>(barsContainer.getChildren().stream()
                .map(r -> (Rectangle) r)
                .toList());

        HighlightListener highlightListener = (i) -> highlightBar(i, timeline, bars, slider);
        SwapListener swapListener = (i, j) -> swapBars(i,j, timeline, bars, slider);

        SortingAlgorithm<Integer> bubbleSort = new BubbleSort<>(numbers);
        bubbleSort.sort(swapListener, highlightListener);

        dehighlightAll(timeline, bars, slider);

        timeline.rateProperty().bind(slider.valueProperty());
        timeline.play();
    }

    private static void highlightBar(int i, Timeline timeline, List<Rectangle> bars, Slider slider) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / slider.getValue()),
                ev -> {
                    bars.get(i).setFill(Color.GREEN);
                    for (int k = 0; k < bars.size(); k++) {
                        if (k != i)
                            bars.get(k).setFill(Color.DODGERBLUE);
                    }
                }
        ));
    }

    private static void swapBars(int i, int j, Timeline timeline, List<Rectangle> bars, Slider slider) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / slider.getValue()),
                ev -> {
                    double h1 = bars.get(i).getHeight();
                    double h2 = bars.get(j).getHeight();
                    bars.get(i).setHeight(h2);
                    bars.get(j).setHeight(h1);
                    bars.get(j).setFill(Color.DODGERBLUE);
                    bars.get(i).setFill(Color.GREEN);
                }
                ));
    }

    private static void dehighlightAll(Timeline timeline, List<Rectangle> bars, Slider slider) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / slider.getValue()),
                ev -> {
                    for (Rectangle bar : bars)
                        bar.setFill(Color.DODGERBLUE);
                }
        ));
    }
}
