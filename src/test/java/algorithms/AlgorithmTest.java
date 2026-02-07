package algorithms;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.SelectionSort;
import sortingAlgorithms.SortingAlgorithm;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmTest {
    static List<SortingAlgorithm<Integer>> algorithms() {
        return List.of(new SelectionSort<>(),
                new BubbleSort<>(),
                new InsertionSort<>());
    }
    @ParameterizedTest
    @MethodSource("algorithms")
    public void SortTest(SortingAlgorithm<Integer> sortingAlgorithm) {
        List<Integer> numbers = new ArrayList<>(List.of(7,8,5,1,4));
        List<Integer> expected = List.of(1,4,5,7,8);
        sortingAlgorithm.setList(numbers);
        sortingAlgorithm.sort();
        assertEquals(expected, numbers);
    }
}
