package controller;

import gui.MainMenuWindow;
import gui.SortingAlgorithmWindow;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class GUIController {
    Stage mainStage;
    MainMenuWindow mainMenuWindow;
    SortingAlgorithmWindow sortingAlgorithmWindow;
    SortingController sortingController;

    public GUIController(Stage stage) {

        this.mainStage = stage;

        mainMenuWindow = new MainMenuWindow(this);
        sortingAlgorithmWindow = new SortingAlgorithmWindow(this);
        sortingController = new SortingController();
    }

    public void showMainMenu() {
        mainMenuWindow.showMainWindow(mainStage);
    }

    public void showSortingAlgorithmMenu() {
        sortingAlgorithmWindow.showSortingWindow(mainStage);
    }

    public void close() {
        mainStage.close();
    }

    public void startSorting(Toggle algorithmToggle, List<Integer> numbers,
                             HBox barsContainer, Slider slider) {
        sortingController.startSortingAlgorithm(algorithmToggle, numbers, barsContainer, slider);
    }
}
