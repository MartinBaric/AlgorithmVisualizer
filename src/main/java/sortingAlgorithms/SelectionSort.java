package sortingAlgorithms;

import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T>{

    public SelectionSort(List<T> numbers, HighlightListener highlightListener, SwapListener swapListener) {
        super(numbers, highlightListener, swapListener);
    }

    public SelectionSort() {
        super();
    }

    @Override
    public void sort() {
        T smallest;
        int smallestIndex;

        for (int i = 0; i < list.size(); i++) {
            smallestIndex = i;
            smallest = list.get(i);
            if (highlightListener != null)
                highlightListener.highlight(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(smallest) < 0) {
                    smallest = list.get(j);
                    smallestIndex = j;
                    if (highlightListener != null)
                        highlightListener.highlight(j);
                }
            }
            if (smallestIndex != i) {
                list.set(smallestIndex, list.get(i));
                list.set(i, smallest);

                if (swapListener != null)
                    swapListener.swap(i, smallestIndex);
            }
        }
    }

}
