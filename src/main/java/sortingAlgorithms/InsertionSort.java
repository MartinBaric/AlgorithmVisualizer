package sortingAlgorithms;

import sortingAlgorithms.enums.SortingType;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

public class InsertionSort <T extends Comparable<T>> extends SortingAlgorithm<T> {

    public InsertionSort(List<T> numbers, HighlightListener highlightListener, SwapListener swapListener) {
        super(numbers, highlightListener, swapListener);
    }

    public InsertionSort() {
        super();
    }

    @Override
    public void sort() {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i-1;

            if (highlightListener != null)
                highlightListener.highlight(i);

            while (j >= 0 && key.compareTo(list.get(j)) < 0) {
                list.set(j+1, list.get(j));
                if (swapListener != null)
                    swapListener.swap(j,j+1);
                j--;
            }

            list.set(j+1, key);
            if (highlightListener != null)
                highlightListener.highlight(j+1);
        }
    }

    @Override
    SortingType getType() {
        return SortingType.INSERTSORT;
    }
}
