package sortingAlgorithms.enums;

import lombok.Getter;
import sortingAlgorithms.BubbleSort;
import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.SelectionSort;
import sortingAlgorithms.SortingAlgorithm;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

@Getter
public enum SortingType {
    BUBBLESORT("Bubblesort") {
        @Override
        public <T extends Comparable<T>> SortingAlgorithm<T>
        create(List<T> list, HighlightListener highlightListener, SwapListener swapListener) {
            return new BubbleSort<>(list, highlightListener, swapListener);
        }
    },

    INSERTSORT("Insertsort") {
        @Override
        public <T extends Comparable<T>> SortingAlgorithm<T>
        create(List<T> list, HighlightListener highlightListener, SwapListener swapListener) {
            return new InsertionSort<>(list, highlightListener, swapListener);
        }
    },
    SELECTSORT("Selectsort") {
        @Override
        public <T extends Comparable<T>> SortingAlgorithm<T>
        create(List<T> list, HighlightListener highlightListener, SwapListener swapListener) {
            return new SelectionSort<>(list, highlightListener, swapListener);
        }
    };

    @Getter
    private final String name;

    SortingType(String name) {
        this.name = name;
    }

    public abstract <T extends Comparable<T>> SortingAlgorithm<T> create(List<T> list,
                         HighlightListener highlightListener, SwapListener swapListener) ;
}
