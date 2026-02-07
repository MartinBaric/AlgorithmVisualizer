package gui;

import csvInput.CSVLoader;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.SelectionSort;
import sortingAlgorithms.SortingAlgorithm;
import sortingAlgorithms.enums.SortingType;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmMenu {
    private static final int WIDTH_SORTING = 900;
    private static final int HEIGHT_SORTING = 600;

    private static final int WIDTH_ALGORITHM_WINDOW = 600;
    private static final int HEIGHT_ALGORITHM_WINDOW = 400;

    private static final float MIN_VALUE = 0.1f;
    private static final float MAX_VALUE = 3.0f;

    private static final int SPACING = 15;
    private static final double BRIGHTNESS = 0.3;

    static void visualizationScene(Stage stage) {
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

        root.setCenter(algorithmWindow);

        RadioButton bubbleSort = new RadioButton(SortingType.BUBBLESORT.getName());
        RadioButton insertSort = new RadioButton(SortingType.INSERTSORT.getName());
        RadioButton selectSort = new RadioButton(SortingType.SELECTSORT.getName());

        bubbleSort.setUserData(SortingType.BUBBLESORT);
        insertSort.setUserData(SortingType.INSERTSORT);
        selectSort.setUserData(SortingType.SELECTSORT);

        ToggleGroup group = new ToggleGroup();
        bubbleSort.setToggleGroup(group);
        insertSort.setToggleGroup(group);
        selectSort.setToggleGroup(group);

        Slider slider = new Slider(MIN_VALUE, MAX_VALUE, (MAX_VALUE + MIN_VALUE) / 2);
        slider.setOrientation(Orientation.HORIZONTAL);
        Button start = new Button("Start");
        Button back = new Button("Back");

        start.setOnAction(e -> startAlgorithm(group.getSelectedToggle(), numbers,
                verticalBars, slider));
        back.setOnAction(e -> MainMenu.setMainGui(stage));

        VBox algorithms = new VBox(SPACING, bubbleSort, insertSort, selectSort);
        VBox controlPanel = new VBox(SPACING, slider, start, algorithms, back);
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

    private static void startAlgorithm(Toggle algorithmToggle, List<Integer> numbers,
                                       HBox barsContainer, Slider slider) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        List<Rectangle> bars = new ArrayList<>(barsContainer.getChildren().stream()
                .map(r -> (Rectangle) r)
                .toList());

        HighlightListener highlightListener = (i) -> highlightBar(i, timeline, bars, slider);
        SwapListener swapListener = (i, j) -> swapBars(i,j, timeline, bars, slider);

        SortingAlgorithm<Integer> sortingAlgorithm = null;

        if (algorithmToggle == null) return;

        switch((SortingType) algorithmToggle.getUserData()) {
            case BUBBLESORT -> sortingAlgorithm = new BubbleSort<>(numbers, highlightListener, swapListener);
            case INSERTSORT -> sortingAlgorithm = new InsertionSort<>(numbers, highlightListener, swapListener);
            case SELECTSORT -> sortingAlgorithm = new SelectionSort<>(numbers, highlightListener, swapListener);
        }
        sortingAlgorithm.sort();

        dehighlightAll(timeline, bars, slider);

        timeline.rateProperty().bind(slider.valueProperty());
        timeline.play();
    }

    private static void highlightBar(int i, Timeline timeline, List<Rectangle> bars, Slider slider) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / slider.getValue()),
                ev -> {
                    markBar(bars.get(i), true);
                    for (int k = 0; k < bars.size(); k++) {
                        if (k != i)
                            markBar(bars.get(k), false);
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
                    markBar(bars.get(j), false);
                    markBar(bars.get(i), true);
                }
        ));
    }

    private static void dehighlightAll(Timeline timeline, List<Rectangle> bars, Slider slider) {
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis((timeline.getKeyFrames().size() + 1) * 100 / slider.getValue()),
                ev -> {
                    for (Rectangle bar : bars)
                        markBar(bar, false);
                }
        ));
    }

    private static ImagePattern getBrickImagePattern() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("moosybrick.jpg");
        if (is == null) {
            throw new RuntimeException("moosybrick.jpg not found in classpath!");
        }

        Image brickImage = new Image(is);
        return new ImagePattern(brickImage, 0, 0, 20, 30, false);
    }

    private static void markBar(Rectangle bar, boolean mark) {
        ColorAdjust adjust = new ColorAdjust();
        double brightness;

        if (mark)
            brightness = BRIGHTNESS;
        else
            brightness = -BRIGHTNESS;

        adjust.setBrightness(brightness);
        bar.setEffect(adjust);
    }
}
