package sortingAlgorithms;

import sortingAlgorithms.enums.SortingType;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    public BubbleSort(List<T> list) {
        super(list);
    }

    @Override
    public void sort(SwapListener swapListener, HighlightListener highlightListener) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (highlightListener != null)
                    highlightListener.highlight(j);

                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    // Ascending bubble
                    T temp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, temp);

                    if (swapListener != null)
                        swapListener.swap(j-1, j);
                }
            }
        }
    }

    @Override
    SortingType getType() {
        return SortingType.BUBBLESORT;
    }
}
