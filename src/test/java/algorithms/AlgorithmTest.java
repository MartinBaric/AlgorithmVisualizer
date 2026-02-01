package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.SortingAlgorithm;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmTest {
    @Test
    public void BubbleSortTest() {
        List<Integer> numbers = new ArrayList<>(List.of(7,8,5,1,4));
        List<Integer> expected = List.of(1,4,5,7,8);
        SortingAlgorithm<Integer> bubbleSort = new BubbleSort<>(numbers);
        bubbleSort.sort(null);
        assertEquals(numbers, expected);
    }
}
