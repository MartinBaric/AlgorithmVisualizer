package sortingAlgorithms;

import sortingAlgorithms.enums.SortingType;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

public class InsertionSort <T extends Comparable<T>> extends SortingAlgorithm<T> {

    public InsertionSort(List<T> list) { super(list); }

    @Override
    public void sort(SwapListener swapListener, HighlightListener highlightListener) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i-1;

            highlightListener.highlight(i);

            while (j >= 0 && key.compareTo(list.get(j)) < 0) {
                list.set(j+1, list.get(j));
                swapListener.swap(j,j+1);
                j--;
            }

            list.set(j+1, key);
            highlightListener.highlight(j+1);
        }
    }

    @Override
    SortingType getType() {
        return SortingType.INSERTSORT;
    }
}
