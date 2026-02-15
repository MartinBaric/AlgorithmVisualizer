package controller;

import animation.SortingAnimation;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import sortingAlgorithms.SortingAlgorithm;
import sortingAlgorithms.enums.SortingType;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.ArrayList;
import java.util.List;

public class SortingController {
    public SortingController() {

    }

    public void startSortingAlgorithm(Toggle algorithmToggle, List<Integer> numbers,
                                      HBox barsContainer, Slider slider) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);

        List<Rectangle> bars = new ArrayList<>(barsContainer.getChildren().stream()
                .map(r -> (Rectangle) r)
                .toList());

        SortingAnimation sortingAnimation = new SortingAnimation(bars, slider, timeline);

        HighlightListener highlightListener = sortingAnimation.createHighlightListener();
        SwapListener swapListener = sortingAnimation.createSwapListener();

        SortingType sortingType = (SortingType) algorithmToggle.getUserData();
        SortingAlgorithm<Integer> sortingAlgorithm = sortingType.create(numbers, highlightListener, swapListener);

        sortingAlgorithm.sort();

        sortingAnimation.dehighlightAll(timeline, bars, slider);

        timeline.rateProperty().bind(slider.valueProperty());
        timeline.play();
    }
}
