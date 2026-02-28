package controller;

import animation.SortingAnimation;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import lombok.NoArgsConstructor;
import sortingAlgorithms.SortingAlgorithm;
import sortingAlgorithms.enums.SortingAlgorithmType;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class SortingController {

    public void startSortingAlgorithm(Toggle algorithmToggle, List<Integer> numbers,
                                      HBox barsContainer, Slider slider) {

        List<Rectangle> bars = new ArrayList<>(barsContainer.getChildren().stream()
                .map(r -> (Rectangle) r)
                .toList());

        SortingAnimation sortingAnimation = new SortingAnimation(bars, slider);

        HighlightListener highlightListener = sortingAnimation.createHighlightListener();
        SwapListener swapListener = sortingAnimation.createSwapListener();

        SortingAlgorithmType sortingAlgorithmType = (SortingAlgorithmType) algorithmToggle.getUserData();
        SortingAlgorithm<Integer> sortingAlgorithm = sortingAlgorithmType.create(numbers, highlightListener, swapListener);

        sortingAlgorithm.sort();

        sortingAnimation.dehighlightAll();
        sortingAnimation.play();
    }
}
